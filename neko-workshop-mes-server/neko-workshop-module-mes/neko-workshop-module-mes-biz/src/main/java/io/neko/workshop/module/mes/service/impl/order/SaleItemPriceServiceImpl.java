package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.ItemPriceExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.SaleItemPriceQueryVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.SaleItemPriceVo;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteModel;
import io.neko.workshop.module.mes.dal.dataobject.order.SaleItemPrice;
import io.neko.workshop.module.mes.dal.mysql.order.SaleItemPriceMapper;
import io.neko.workshop.module.mes.service.order.SaleItemPriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 15:29
 */
@Service
@RequiredArgsConstructor
public class SaleItemPriceServiceImpl implements SaleItemPriceService {

    private final SaleItemPriceMapper saleItemPriceMapper;


    @Override
    public Integer saveOrderSalePrice(SaleItemPrice orderSalePrice) {
        orderSalePrice.setPriceKg(orderSalePrice.getPriceTon().divide(new BigDecimal("1000")));
        return saleItemPriceMapper.insert(orderSalePrice);
    }


    @Override
    public Integer removeOrderSalePrice(List<Long> ids) {
        return saleItemPriceMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateOrderSalePrice(SaleItemPrice orderSalePrice) {
        orderSalePrice.setPriceKg(orderSalePrice.getPriceTon().divide(new BigDecimal("1000")));
        return saleItemPriceMapper.updateById(orderSalePrice);
    }

    @Override
    public SaleItemPrice findOrderSalePriceById(Long id) {
        return saleItemPriceMapper.selectById(id);
    }

    @Override
    public PageResult<SaleItemPrice> findPage(SaleItemPriceQueryVo orderSalePriceQueryVo) {
        return saleItemPriceMapper.findPage(orderSalePriceQueryVo);
    }



    @Override
    public  List<SaleItemPriceVo>  findPrice(String copper) {
        return saleItemPriceMapper.findPrice(copper);
    }

    @Override
    public List<ItemPriceExportVo> exportData() {
        List<SaleItemPrice> saleItemPriceList = saleItemPriceMapper.selectList();
        ArrayList<ItemPriceExportVo> itemPriceExportVo = new ArrayList<>(saleItemPriceList.size());
        saleItemPriceList.forEach(dict -> {
            ItemPriceExportVo excelDictDTO = new ItemPriceExportVo();
            BeanUtils.copyProperties(dict, excelDictDTO);
            itemPriceExportVo.add(excelDictDTO);
        });
        return itemPriceExportVo;
    }


}
