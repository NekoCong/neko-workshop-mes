package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.StandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:43
 */
public interface StandardService {

    Integer saveInspectStandard(Standard inspectStandard);

    Integer removeInspectStandard(List<Long> ids);

    Standard findStandardById(Long id);

    Integer updateInspectStandard(Standard inspectStandard);

    PageResult<Standard> findPage(StandardQueryVo inspectStandardPageReqVO);

    List<Standard> findStandardList();

    List<Standard> findStandardByProcessCode(String code);
}
