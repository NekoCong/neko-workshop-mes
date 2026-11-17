package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.SemiLabelAllExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.SemiLabelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.SemiLabelQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.SemiLabel;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/9 17:06
 */
public interface SemiLabelService {

    PageResult<SemiLabel> findPage(SemiLabelQueryVo semiLabelQueryVo);

    Integer saveSemiLabel(SemiLabel semiLabel);

    Integer updateSemiLabel(SemiLabel semiLabel);

    Integer removeSemiLabelByIds(List<Long> ids);

    SemiLabel findSemiLabelById(Long id);

    List<SemiLabel> listAll();
    List<SemiLabelExportVo> listData();

    List<SemiLabelAllExportVo> listAllDataByIds(List<Integer> ids);
}
