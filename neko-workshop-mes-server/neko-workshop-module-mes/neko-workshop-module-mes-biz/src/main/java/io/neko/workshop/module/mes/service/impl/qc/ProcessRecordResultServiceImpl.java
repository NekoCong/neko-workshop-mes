package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProcessRecordResult;
import io.neko.workshop.module.mes.dal.mysql.qc.ProcessRecordResultMapper;
import io.neko.workshop.module.mes.service.qc.ProcessRecordResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/11 17:34
 */
@Service
@RequiredArgsConstructor
public class ProcessRecordResultServiceImpl implements ProcessRecordResultService {

    private final ProcessRecordResultMapper processFormResultMapper;

    @Override
    public void insertProcessFormResult(ProcessRecordResult processFormResult) {
        processFormResultMapper.insert(processFormResult);
    }

    @Override
    public List<ProcessRecordResult> selectByProcessFormId(Long recordId) {
        LambdaQueryWrapperX<ProcessRecordResult> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(ProcessRecordResult::getRecordId,recordId);
        return processFormResultMapper.selectList(queryWrapper);
    }

    @Override
    public List<ProcessRecordResult> selectByResultStatus(Long recordId, String resultStatus) {
        LambdaQueryWrapperX<ProcessRecordResult> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(ProcessRecordResult::getRecordId,recordId);
        queryWrapper.eq(ProcessRecordResult::getResultStatus,resultStatus);
        return processFormResultMapper.selectList(queryWrapper);
    }
}
