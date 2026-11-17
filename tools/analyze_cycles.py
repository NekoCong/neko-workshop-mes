# -*- coding: utf-8 -*-
"""分析 Spring Bean 之间的构造依赖环（Strongly Connected Components）。"""
import os
import re
import sys

ROOT = sys.argv[1] if len(sys.argv) > 1 else r'E:\workspace\neko-workshop-mes'
TREES = ['neko-workshop-mes-server', 'neko-workshop']

CLASS_DECL = re.compile(r'^(?P<indent>[ \t]*)(?:public\s+|protected\s+|private\s+)?(?:abstract\s+|final\s+)*class\s+(?P<name>\w+)(?P<rest>[^{]*)\{', re.M)
BEAN_ANN = re.compile(r'@(Service|Component|RestController|Controller|Repository|Configuration)\b')
FIELD = re.compile(r'^[ \t]*(?:private|protected|public)\s+final\s+(?P<type>[\w<>\[\],\.\? ]+?)\s+(?P<name>\w+)\s*;', re.M)


def scan():
    files = []
    for tree in TREES:
        for dirpath, dirnames, filenames in os.walk(os.path.join(ROOT, tree)):
            parts = dirpath.split(os.sep)
            if 'target' in parts or 'node_modules' in parts:
                continue
            if os.sep + 'src' + os.sep + 'main' + os.sep + 'java' + os.sep not in dirpath + os.sep:
                continue
            for fn in filenames:
                if fn.endswith('.java'):
                    files.append(os.path.join(dirpath, fn))

    classes = {}   # simple name -> info
    impls = {}     # interface simple name -> [impl simple names]
    for path in files:
        with open(path, 'r', encoding='utf-8', errors='replace') as fh:
            text = fh.read()
        m = CLASS_DECL.search(text)
        if not m:
            continue
        name = m.group('name')
        rest = m.group('rest')
        is_bean = bool(BEAN_ANN.search(text[:m.start()]))
        impl_of = []
        # implements X, Y
        im = re.search(r'implements\s+([^{]+)', rest)
        if im:
            for part in im.group(1).split(','):
                base = part.split('<')[0].strip()
                if base and re.match(r'^[A-Z]\w*$', base):
                    impl_of.append(base)
        deps = []
        lazy = set()
        # 字段前一行是否带 @Lazy
        lines_txt = text.split('\n')
        for fm in FIELD.finditer(text):
            ftype = fm.group('type').strip()
            base = ftype.split('<')[0].strip()
            if base in ('String', 'int', 'long', 'boolean', 'Integer', 'Long', 'Boolean',
                        'List', 'Map', 'Set', 'Collection', 'TemplateEngine', 'Object'):
                continue
            if '<' in ftype:
                continue
            # 找该字段所在行号
            line_no = text[:fm.start()].count('\n')
            prev = lines_txt[line_no - 1] if line_no > 0 else ''
            if '@Lazy' in prev:
                lazy.add(fm.group('name'))
                continue  # 已断环，不计入硬依赖
            deps.append(base)
        classes[name] = {'path': path, 'bean': is_bean, 'impl_of': impl_of,
                         'deps': deps, 'lazy': lazy, 'text': text}
        for i in impl_of:
            impls.setdefault(i, []).append(name)
    return classes, impls


def build_graph(classes, impls):
    """节点=具体类；边=构造依赖（已 @Lazy 的边不参与，视为已断环）。"""
    graph = {}
    edges = {}
    for name, info in classes.items():
        if not info['bean']:
            continue
        out = []
        for dep in info['deps']:
            target = None
            if dep in classes and classes[dep]['bean'] and not classes[dep]['impl_of']:
                target = dep
            elif dep in impls and len(impls[dep]) == 1:
                target = impls[dep][0]
            elif dep in classes and classes[dep]['bean']:
                if len(impls.get(dep, [])) == 1:
                    target = impls[dep][0]
                else:
                    target = dep
            if target and target in classes:
                out.append(target)
                edges[(name, target)] = dep
        graph[name] = sorted(set(out))
    return graph, edges


def scc(graph):
    """Tarjan 强连通分量。"""
    index = {}
    low = {}
    stack = []
    on_stack = set()
    result = []
    counter = [0]

    def strongconnect(v):
        index[v] = counter[0]
        low[v] = counter[0]
        counter[0] += 1
        stack.append(v)
        on_stack.add(v)
        for w in graph.get(v, []):
            if w not in index:
                strongconnect(w)
                low[v] = min(low[v], low[w])
            elif w in on_stack:
                low[v] = min(low[v], index[w])
        if low[v] == index[v]:
            comp = []
            while True:
                w = stack.pop()
                on_stack.discard(w)
                comp.append(w)
                if w == v:
                    break
            result.append(comp)

    sys.setrecursionlimit(100000)
    for v in list(graph):
        if v not in index:
            strongconnect(v)
    return [c for c in result if len(c) > 1]


def main():
    classes, impls = scan()
    graph, edges = build_graph(classes, impls)
    comps = scc(graph)
    print('beans=%d edges=%d cycles(SCC>1)=%d' % (len(graph), sum(len(v) for v in graph.values()), len(comps)))
    for i, comp in enumerate(comps, 1):
        members = set(comp)
        print('\n=== 环 %d（%d 个类）===' % (i, len(comp)))
        for c in sorted(comp):
            internal = [d for d in graph.get(c, []) if d in members]
            print('  %s -> %s' % (c, ', '.join(internal) if internal else '(无内部边)'))


if __name__ == '__main__':
    main()
