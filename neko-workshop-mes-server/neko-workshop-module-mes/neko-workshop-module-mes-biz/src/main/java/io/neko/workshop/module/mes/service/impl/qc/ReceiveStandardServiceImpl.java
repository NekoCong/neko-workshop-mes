package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ReceiveStandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveStandard;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;
import io.neko.workshop.module.mes.dal.mysql.qc.ReceiveStandardMapper;
import io.neko.workshop.module.mes.service.qc.ReceiveStandardService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/26 11:01
 */
@Service
@RequiredArgsConstructor
public class ReceiveStandardServiceImpl implements ReceiveStandardService {

    private final ReceiveStandardMapper receiveStandardMapper;

    @Override
    public PageResult<ReceiveStandard> findPage(ReceiveStandardQueryVo receiveStandardQueryVo) {
        return receiveStandardMapper.findPage(receiveStandardQueryVo);
    }

    @Override
    public List<ReceiveStandard> findReceiveStandardList() {
        return receiveStandardMapper.selectList();
    }

    @Override
    public List<ReceiveStandard> findReceiveStandardByItemCode(String itemCode) {
        return receiveStandardMapper.findReceiveStandardByItemCode(itemCode);
    }

    @Override
    public Integer saveReceiveStandard(ReceiveStandard receiveStandard) {
        return receiveStandardMapper.insert(receiveStandard);
    }

    @Override
    public ReceiveStandard findReceiveStandardById(Long id) {
        return receiveStandardMapper.selectById(id);
    }

    @Override
    public Integer updateReceiveStandard(ReceiveStandard receiveStandard) {
        return receiveStandardMapper.updateById(receiveStandard);
    }

    @Override
    public Integer removeReceiveStandard(List<Long> ids) {
        return receiveStandardMapper.deleteBatchIds(ids);
    }

    @Override
    public ReceiveStandard findReceiveStandardByItemCode(String itemCode, String enableFlag) {
        LambdaQueryWrapperX<ReceiveStandard> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.like(ReceiveStandard::getItemCode, itemCode);
        queryWrapperX.eq(ReceiveStandard::getEnableFlag, enableFlag);
        return receiveStandardMapper.selectOne(queryWrapperX);
    }
}
