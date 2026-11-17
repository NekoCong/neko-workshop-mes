# -*- coding: utf-8 -*-
"""在指定类的指定构造字段上补 @Lazy，打破构造注入的循环依赖。"""
import io
import os
import re
import sys

ROOT = r'E:\workspace\neko-workshop-mes'

# (相对路径, 字段名, 说明) —— 每个环只断一条边
TARGETS = [
    # 环1：system 权限/租户大环
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/tenant/TenantServiceImpl.java',
     'roleService', 'TenantServiceImpl -> RoleServiceImpl'),
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/tenant/TenantServiceImpl.java',
     'menuService', 'TenantServiceImpl -> MenuServiceImpl'),
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/permission/PermissionServiceImpl.java',
     'userService', 'PermissionServiceImpl -> AdminUserServiceImpl'),
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/permission/MenuServiceImpl.java',
     'permissionService', 'MenuServiceImpl -> PermissionServiceImpl'),
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/user/AdminUserServiceImpl.java',
     'permissionService', 'AdminUserServiceImpl -> PermissionServiceImpl'),
    # 环2：短信渠道/模板
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/sms/SmsChannelServiceImpl.java',
     'smsTemplateService', 'SmsChannelServiceImpl -> SmsTemplateServiceImpl'),
    # 环3：BPM 流程实例/任务
    ('neko-workshop/neko-workshop-module-bpm/neko-workshop-module-bpm-biz/src/main/java/io/neko/workshop/module/bpm/service/task/BpmProcessInstanceServiceImpl.java',
     'taskService', 'BpmProcessInstanceServiceImpl -> BpmTaskServiceImpl'),
    # 环4：字典类型/数据
    ('neko-workshop/neko-workshop-module-system/neko-workshop-module-system-biz/src/main/java/io/neko/workshop/module/system/service/dict/DictTypeServiceImpl.java',
     'dictDataService', 'DictTypeServiceImpl -> DictDataServiceImpl'),
    # 环5：BPM 模型/任务分配规则
    ('neko-workshop/neko-workshop-module-bpm/neko-workshop-module-bpm-biz/src/main/java/io/neko/workshop/module/bpm/service/definition/BpmModelServiceImpl.java',
     'taskAssignRuleService', 'BpmModelServiceImpl -> BpmTaskAssignRuleServiceImpl'),
]

LAZY_IMPORT = 'import org.springframework.context.annotation.Lazy;'


def patch(rel, field, why):
    path = os.path.join(ROOT, rel)
    if not os.path.exists(path):
        return 'MISSING %s' % rel
    with io.open(path, 'r', encoding='utf-8', newline='') as fh:
        raw = fh.read()
    nl = '\r\n' if '\r\n' in raw else '\n'

    decl_re = re.compile(
        r'(?P<indent>[ \t]*)(?:(?:private|protected|public)\s+)?final\s+[\w<>\[\],\.\? ]+\s+'
        + re.escape(field) + r'\s*;')
    m = decl_re.search(raw)
    if not m:
        return 'NO FIELD %s.%s' % (os.path.basename(rel), field)
    # 已经带 @Lazy 就跳过
    before = raw[:m.start()]
    last_line = before.rstrip('\r\n').split('\n')[-1] if before.strip() else ''
    if '@Lazy' in last_line:
        return 'ALREADY %s.%s' % (os.path.basename(rel), field)

    indent = m.group('indent')
    ins = '%s@Lazy // %s%s' % (indent, why, nl)
    raw = raw[:m.start()] + ins + raw[m.start():]

    if LAZY_IMPORT not in raw:
        anchor = 'import org.springframework.stereotype.Service;'
        if anchor in raw:
            raw = raw.replace(anchor, LAZY_IMPORT + nl + anchor, 1)
        else:
            # 退而求其次：插到第一个 import 之后
            im = re.search(r'^import [^\n]*\n', raw, re.M)
            if im:
                raw = raw[:im.end()] + LAZY_IMPORT + nl + raw[im.end():]

    with io.open(path, 'w', encoding='utf-8', newline='') as fh:
        fh.write(raw)
    return 'PATCHED %s.%s' % (os.path.basename(rel), field)


def main():
    for rel, field, why in TARGETS:
        print(patch(rel, field, why))


if __name__ == '__main__':
    main()
