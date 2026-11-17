package io.neko.workshop.module.mes.controller.admin.auto;

import io.neko.workshop.module.mes.dal.dataobject.auto.AutoCodePart;

public interface PartTypeTemplate {
    /**
     * 分段的处理规则
     * @param sysAutoCodePart
     * @return
     */
    String partHandle(AutoCodePart sysAutoCodePart);
}

