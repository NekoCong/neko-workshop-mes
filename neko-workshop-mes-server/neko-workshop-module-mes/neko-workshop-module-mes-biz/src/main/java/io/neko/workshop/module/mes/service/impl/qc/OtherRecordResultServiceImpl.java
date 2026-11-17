package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.module.mes.controller.admin.qc.vo.OtherStandardResultVo;
import io.neko.workshop.module.mes.dal.mysql.qc.OtherRecordResultMapper;
import io.neko.workshop.module.mes.service.qc.OtherRecordResultService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 14:28
 */
@Service
@RequiredArgsConstructor
public class OtherRecordResultServiceImpl implements OtherRecordResultService {

    private final OtherRecordResultMapper otherRecordResultMapper;

    @Override
    public OtherStandardResultVo getAcount(OtherStandardResultVo otherStandardResultVo) {
        return otherRecordResultMapper.getAcount(otherStandardResultVo);
    }
}
