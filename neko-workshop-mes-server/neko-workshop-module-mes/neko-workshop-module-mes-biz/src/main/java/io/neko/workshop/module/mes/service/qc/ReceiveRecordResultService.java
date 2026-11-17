package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveRecordResult;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 14:59
 */
public interface ReceiveRecordResultService {

    Integer insertReceiveRecordResult(ReceiveRecordResult receiveRecordResult);

    List<ReceiveRecordResult> selectByResultStatus(Long recordId, String resultStatus);

    List<ReceiveRecordResult> selectByStatus(String recNumber, String resultStatus);

    List<ReceiveRecordResult> selectReceiveRecordResult(String itemCode,String recNumber, String resultStatus);
}
