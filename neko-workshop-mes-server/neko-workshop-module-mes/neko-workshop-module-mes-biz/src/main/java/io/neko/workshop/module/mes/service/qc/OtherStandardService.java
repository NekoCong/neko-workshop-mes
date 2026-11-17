package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherStandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherStandard;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherStandardItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
public interface OtherStandardService {

    PageResult<OtherStandard>  findPage(OtherStandardQueryVo otherStandardQueryVo);

    List<OtherStandard> findOtherStandardList();

    List<OtherStandard> findOtherStandardByModel(String model);

    List<OtherStandard> findOtherStandardBySpec(String spec);

    List<OtherStandard> findOtherStandardByLineType(String lineType);

    Integer saveOtherStandard(OtherStandard otherStandard);

    Integer updateOtherStandard(OtherStandard otherStandard);

    OtherStandard findOtherStandard(Long id);

    Integer deleteOtherStandard(List<Long> ids);

    OtherStandard findOtherStandard(String model, String spec, String lineType);
}
