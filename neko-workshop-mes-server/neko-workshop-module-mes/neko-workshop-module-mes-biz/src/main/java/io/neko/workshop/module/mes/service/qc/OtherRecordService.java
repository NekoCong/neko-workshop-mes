package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherRecordExcelVo;
import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherRecordQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecord;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:43
 */
public interface OtherRecordService {

    PageResult<OtherRecord> findPage(OtherRecordQueryVo otherRecordQueryVo);

    List<OtherRecordExcelVo> exportDate();

    Integer saveOtherRecord(OtherRecord otherRecord);

    Integer updateOtherRecord(OtherRecord otherRecord);


    OtherRecord findOtherRecordById(Long id);
}
