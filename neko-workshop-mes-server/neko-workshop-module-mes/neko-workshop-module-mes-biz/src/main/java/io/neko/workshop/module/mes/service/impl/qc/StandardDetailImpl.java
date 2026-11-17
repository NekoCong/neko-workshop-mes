package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;
import io.neko.workshop.module.mes.dal.dataobject.qc.StandardDetail;
import io.neko.workshop.module.mes.dal.mysql.qc.StandardDetailMapper;
import io.neko.workshop.module.mes.service.qc.StandardDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/11 17:19
 */
@Service
@RequiredArgsConstructor
public class StandardDetailImpl implements StandardDetailService {

    private final StandardDetailMapper standardDetailMapper;

    @Override
    public Integer saveStandardDetail(StandardDetail standardDetail) {
        return standardDetailMapper.insert(standardDetail);
    }

    @Override
    public Integer removeCode(String inspectCode) {
        LambdaQueryWrapperX<StandardDetail> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(StandardDetail::getProcessStandardCode,inspectCode);
        return standardDetailMapper.delete(queryWrapperX);
    }

    @Override
    public List<StandardDetail> findStandardDetailByCode(String inspectCode) {
        LambdaQueryWrapperX<StandardDetail> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(StandardDetail::getProcessStandardCode,inspectCode);
        return standardDetailMapper.selectList(queryWrapperX);
    }

    @Override
    public Integer removeDetailByCode(String deviceCode,String standardCode) {
        LambdaQueryWrapperX<StandardDetail> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(StandardDetail::getDetectionDevice,deviceCode);
        queryWrapperX.eq(StandardDetail::getProcessStandardCode,standardCode);
        return standardDetailMapper.delete(queryWrapperX);
    }

    @Override
    public StandardDetail findStandardDetailByCondition(String inspectName, String inspectStand, String inspectDevice, String inspectCode) {
        LambdaQueryWrapperX<StandardDetail> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(StandardDetail::getProcessStandardCode,inspectCode);
        queryWrapperX.eq(StandardDetail::getDetectionName,inspectName);
        queryWrapperX.eq(StandardDetail::getDetectionStandard,inspectStand);
        queryWrapperX.eq(StandardDetail::getDetectionDevice,inspectDevice);
        return standardDetailMapper.selectOne(queryWrapperX);
    }


    @Override
    public List<StandardDetail> findStandardByProcessCodeAndEnableFlag(String code, String enableFlag) {
        LambdaQueryWrapperX<StandardDetail> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(StandardDetail::getProcessCode, code);
        queryWrapperX.eq(StandardDetail::getEnableFlag,enableFlag);
        return standardDetailMapper.selectList(queryWrapperX);
    }
}
