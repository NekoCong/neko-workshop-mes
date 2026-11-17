package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProcessRecordResult;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveRecordResult;
import io.neko.workshop.module.mes.dal.mysql.qc.ReceiveRecordResultMapper;
import io.neko.workshop.module.mes.service.qc.ReceiveRecordResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 14:59
 */
@Service
@RequiredArgsConstructor
public class ReceiveRecordResultServiceImpl implements ReceiveRecordResultService {

    private final ReceiveRecordResultMapper receiveRecordResultMapper;

    @Override
    public Integer insertReceiveRecordResult(ReceiveRecordResult receiveRecordResult) {
        return receiveRecordResultMapper.insert(receiveRecordResult);
    }

    @Override
    public List<ReceiveRecordResult> selectByResultStatus(Long recordId, String resultStatus) {
        LambdaQueryWrapperX<ReceiveRecordResult> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(ReceiveRecordResult::getRecordId,recordId);
        queryWrapper.eq(ReceiveRecordResult::getResultStatus,resultStatus);
        return receiveRecordResultMapper.selectList(queryWrapper);
    }

    @Override
    public List<ReceiveRecordResult> selectByStatus(String recNumber, String resultStatus) {
        LambdaQueryWrapperX<ReceiveRecordResult> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(ReceiveRecordResult::getRecNumber,recNumber);
        queryWrapper.eq(ReceiveRecordResult::getResultStatus,resultStatus);
        return receiveRecordResultMapper.selectList(queryWrapper);
    }

    @Override
    public List<ReceiveRecordResult> selectReceiveRecordResult(String itemCode, String recNumber, String resultStatus) {
        LambdaQueryWrapperX<ReceiveRecordResult> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(ReceiveRecordResult::getItemCode,itemCode);
        queryWrapper.eq(ReceiveRecordResult::getRecNumber,recNumber);
        queryWrapper.eq(ReceiveRecordResult::getResultStatus,resultStatus);
        return receiveRecordResultMapper.selectList(queryWrapper);
    }
}
