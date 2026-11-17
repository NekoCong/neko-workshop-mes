package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ReceiveRecordExcelVo;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ReceiveRecordQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveRecord;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 14:59
 */
public interface ReceiveRecordService {

    PageResult<ReceiveRecord> findPage(ReceiveRecordQueryVo receiveRecordQueryVo);

    List<ReceiveRecordExcelVo> exportData();

    Integer updateReceiveRecord(ReceiveRecord receiveRecord);

    ReceiveRecord findReceiveRecordById(Long id);

    Integer updateStatus(Long id,String status);

    Integer updateReceiveRecords(ReceiveRecord receiveRecord);

    List<ReceiveRecord> findReceiveRecordByRecNumber(String recNumber);

    List<ReceiveRecord> findReceiveRecordByItemCode(String itemCode);
}
