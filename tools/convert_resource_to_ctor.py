# -*- coding: utf-8 -*-
"""
把 Spring 字段注入 (@Resource) 批量转换为构造器注入。

转换后形态：
    @Service
    @RequiredArgsConstructor
    public class FooServiceImpl implements FooService {
        private final FooMapper fooMapper;
    }

规则：
1. 删除 @Resource（含其上的 @Lazy，迁移到字段上，由 lombok.copyableAnnotations 复制到构造参数）。
2. 字段声明补 final，修饰符与字段名保持不变（兼容原有的直接字段访问）。
3. 类声明上补 @RequiredArgsConstructor，并补 import。
4. 清理不再使用的 import：jakarta.annotation.Resource / org.springframework.context.annotation.Lazy。
5. 保留原文件的换行风格（CRLF 文件仍写回 CRLF）。

不自动处理（由调用方人工介入，脚本只报告）：
- 类中已有显式无参构造器（构造注入会与之冲突）。
- @Resource 标注在方法上（setter 注入需改写方法体）。
"""
import os
import re
import sys

RESOURCE_ANN = re.compile(r'^(\s*)@Resource(?:\s*//.*)?\s*$')
LAZY_ANN = re.compile(r'^(\s*)@Lazy(?:\s*//.*)?\s*$')
FIELD_DECL = re.compile(
    r'^(?P<indent>\s*)(?P<mod>(?:private|protected|public)\s+)?'
    r'(?P<type>[\w<>\[\],\.\? ]+?)\s+(?P<name>\w+)\s*;\s*$'
)
CLASS_DECL = re.compile(
    r'^(?P<indent>[ \t]*)(?P<mods>(?:public\s+|protected\s+|private\s+)?'
    r'(?:abstract\s+|final\s+|static\s+)*)class\s+(?P<name>\w+)',
    re.M,
)
EXPLICIT_NOARG_CTOR = re.compile(r'^\s*(?:public|private|protected)\s+(?P<cls>\w+)\s*\(\s*\)\s*\{', re.M)

# 单值注入时无法按类型唯一确定的接口（集合注入不受影响）
MULTI_IMPL = {
    'PayClientConfig', 'SmsCodeMapping', 'IdempotentKeyResolver', 'JobHandler',
    'TypeHandler', 'BpmTaskAssignScript', 'PartTypeTemplate', 'DesensitizationHandler',
    'Converter', 'WebMvcConfigurer', 'FileClientConfig',
}

# 含显式无参构造器，需人工改写构造器
SKIP_FILES = {
    'RedisCaptchaServiceImpl.java',
    'BpmProcessInstanceEventListener.java',
    'BpmTaskEventListener.java',
    'CodegenEngine.java',
}


def is_collection(ftype):
    return re.match(r'^(?:List|Set|Collection|Iterable)\s*<', ftype) is not None


def convert_text(text, filename):
    """返回 (new_text, changed, notes)。text 已规范化为 \\n 换行。"""
    base = os.path.basename(filename)
    if base in SKIP_FILES:
        return text, False, ['skip: 含显式无参构造器，需人工改写']

    lines = text.split('\n')
    notes = []
    out = []
    pending_lazy = None
    injected = 0
    seen_class = False
    i = 0

    while i < len(lines):
        line = lines[i]

        if not seen_class and CLASS_DECL.match(line):
            seen_class = True

        m = RESOURCE_ANN.match(line)
        if m:
            # 找到注解真正修饰的目标（跳过空行与紧随的 @Lazy）
            j = i + 1
            extra_annos = []
            while j < len(lines):
                nxt = lines[j].strip()
                if nxt == '':
                    j += 1
                    continue
                if LAZY_ANN.match(lines[j]):
                    extra_annos.append(lines[j].strip())
                    j += 1
                    continue
                break
            if j >= len(lines):
                out.append(line)
                i += 1
                continue

            fm = FIELD_DECL.match(lines[j])
            if not fm:
                # 方法注入：保留原样，交由人工处理
                notes.append('method: %s' % lines[j].strip()[:70])
                out.append(line)
                i += 1
                continue

            ftype = fm.group('type').strip()
            simple = ftype.split('<')[0].strip()
            inner = ftype.split('<')[1].split('>')[0].strip() if '<' in ftype else ''
            if not is_collection(ftype) and (simple in MULTI_IMPL or inner in MULTI_IMPL):
                notes.append('keep: 单值多实现接口 %s %s' % (ftype, fm.group('name')))
                out.append(line)
                i += 1
                continue

            indent_f = fm.group('indent')
            mod = (fm.group('mod') or 'private').strip()
            decl = '%s%s final %s %s;' % (indent_f, mod, ftype, fm.group('name'))
            annos = []
            if pending_lazy:
                annos.append(pending_lazy)
            annos.extend(extra_annos)
            for a in annos:
                decl = '%s%s\n%s' % (indent_f, a, decl)
            out.append(decl)
            notes.append('ok: %s' % fm.group('name'))
            injected += 1
            pending_lazy = None
            i = j + 1
            continue

        m = LAZY_ANN.match(line)
        if m:
            pending_lazy = line.strip()
            i += 1
            continue

        pending_lazy = None
        out.append(line)
        i += 1

    if injected == 0:
        return text, False, notes

    new_text = '\n'.join(out)

    # 补 @RequiredArgsConstructor
    if '@RequiredArgsConstructor' not in new_text:
        def add_ann(mo):
            return '%s@RequiredArgsConstructor\n%s' % (mo.group('indent'), mo.group(0))

        new_text, n = CLASS_DECL.subn(add_ann, new_text, count=1)
        if n == 0:
            return text, False, notes + ['error: 未定位到类声明']

    if 'import lombok.RequiredArgsConstructor;' not in new_text:
        m2 = re.search(r'^import lombok\.[^\n]*\n', new_text, re.M)
        if m2:
            new_text = new_text[:m2.start()] + 'import lombok.RequiredArgsConstructor;\n' + new_text[m2.start():]
        else:
            imports = list(re.finditer(r'^import [^\n]*\n', new_text, re.M))
            pos = imports[-1].end() if imports else 0
            new_text = new_text[:pos] + 'import lombok.RequiredArgsConstructor;\n' + new_text[pos:]

    # 清理不再使用的 import
    if not re.search(r'@Resource\b', new_text):
        new_text = re.sub(r'^import jakarta\.annotation\.Resource;\n', '', new_text, flags=re.M)
    if not re.search(r'@Lazy\b', new_text):
        new_text = re.sub(r'^import org\.springframework\.context\.annotation\.Lazy;\n', '', new_text, flags=re.M)

    return new_text, True, notes


def convert_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace', newline='') as fh:
        raw = fh.read()

    crlf = '\r\n' in raw
    text = raw.replace('\r\n', '\n').replace('\r', '\n')

    new_text, changed, notes = convert_text(text, path)
    if not changed:
        return None, False, notes

    out = new_text.replace('\n', '\r\n') if crlf else new_text
    with open(path, 'w', encoding='utf-8', newline='') as fh:
        fh.write(out)
    return out, True, notes


def main():
    root = sys.argv[1]
    apply = '--apply' in sys.argv

    changed, skipped, kept = 0, [], []
    for dirpath, _dirnames, filenames in os.walk(root):
        parts = dirpath.split(os.sep)
        if any(p in ('target', 'node_modules', 'tools') or p.startswith('.') for p in parts):
            continue
        for fn in filenames:
            if not fn.endswith('.java'):
                continue
            path = os.path.join(dirpath, fn)
            if os.sep + 'src' + os.sep + 'main' + os.sep + 'java' + os.sep not in path:
                continue
            with open(path, 'r', encoding='utf-8', errors='replace') as fh:
                if '@Resource' not in fh.read():
                    continue
            if apply:
                _, did, notes = convert_file(path)
            else:
                with open(path, 'r', encoding='utf-8', errors='replace', newline='') as fh:
                    raw = fh.read()
                text = raw.replace('\r\n', '\n').replace('\r', '\n')
                _, did, notes = convert_text(text, path)
            rel = os.path.relpath(path, root)
            if did:
                changed += 1
            else:
                skipped.append((rel, '; '.join(notes) or 'no-change'))
            for n in notes:
                if n.startswith(('keep', 'method', 'error')):
                    kept.append('%s :: %s' % (rel, n))

    print('changed=%d skipped=%d' % (changed, len(skipped)))
    if skipped:
        print('--- skipped ---')
        for p, why in skipped:
            print('  %s -> %s' % (p, why))
    if kept:
        print('--- needs manual review ---')
        for k in kept:
            print('  ' + k)


if __name__ == '__main__':
    main()
