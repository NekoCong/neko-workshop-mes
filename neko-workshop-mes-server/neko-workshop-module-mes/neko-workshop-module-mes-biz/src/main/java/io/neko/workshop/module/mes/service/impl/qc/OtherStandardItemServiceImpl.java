package io.neko.workshop.module.mes.service.impl.qc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.OtherStandardItem;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveStandardItem;
import io.neko.workshop.module.mes.dal.mysql.qc.OtherStandardItemMapper;
import io.neko.workshop.module.mes.service.qc.OtherStandardItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
@Service
@RequiredArgsConstructor
public class OtherStandardItemServiceImpl implements OtherStandardItemService {

    private final OtherStandardItemMapper otherStandardItemMapper;


    @Override
    public Integer saveOtherStandardItem(OtherStandardItem otherStandardItem) {
        return otherStandardItemMapper.insert(otherStandardItem);
    }

    @Override
    public Integer deleteOtherStandardItem(String otherStandardNumber) {
        LambdaQueryWrapperX<OtherStandardItem> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        lambdaQueryWrapperX.eq(OtherStandardItem::getOtherStandardNumber,otherStandardNumber);
        return otherStandardItemMapper.delete(lambdaQueryWrapperX);
    }

    @Override
    public Integer removeOtherStandardItemByCode(String deviceCode, String otherStandardNumber) {
        LambdaQueryWrapperX<OtherStandardItem> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        lambdaQueryWrapperX.eq(OtherStandardItem::getDevice,deviceCode);
        lambdaQueryWrapperX.eq(OtherStandardItem::getOtherStandardNumber,otherStandardNumber);
        return otherStandardItemMapper.delete(lambdaQueryWrapperX);
    }

    @Override
    public List<OtherStandardItem> findOtherStandardItemByCode(String otherStandardNumber) {
        LambdaQueryWrapper<OtherStandardItem> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OtherStandardItem::getOtherStandardNumber,otherStandardNumber);
        return otherStandardItemMapper.selectList(lambdaQueryWrapper);
    }


}
