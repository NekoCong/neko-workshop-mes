package io.neko.workshop.module.mes.service.impl.order;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.common.UserConstants;
import io.neko.workshop.module.mes.controller.admin.order.vo.ProductPickAllExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ProductPickExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ProductPickQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.ProductPick;
import io.neko.workshop.module.mes.dal.dataobject.order.ProductPickItem;
import io.neko.workshop.module.mes.dal.mysql.order.ProductPickItemMapper;
import io.neko.workshop.module.mes.dal.mysql.order.ProductPickMapper;
import io.neko.workshop.module.mes.service.order.ProductPickService;
import io.neko.workshop.module.mes.utils.AutoCodeUtil;
import io.neko.workshop.module.mes.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/21 14:12
 */
@Service
@RequiredArgsConstructor
public class ProductPickServiceImpl implements ProductPickService {


    private final ProductPickMapper productPickMapper;

    private final ProductPickItemMapper productPickItemMapper;

    private final AutoCodeUtil autoCodeUtil;

    @Override
    public PageResult<ProductPick> findPage(ProductPickQueryVo productPickQueryVo) {
        return productPickMapper.findPage(productPickQueryVo);
    }

    @Override
    public Integer saveProductPick(ProductPick productPick) {
        productPick.setNumber(autoCodeUtil.genSerialCode(UserConstants.PRODUCT_PICK_CODE,null));
        for(ProductPickItem productPickItem: productPick.getProductPickItemList()){
            ProductPickItem item = new ProductPickItem();
            item.setPickNumber(productPick.getNumber()).setItemCode(productPickItem.getItemCode()).setItemName(productPickItem.getItemName()).setModel(productPickItem.getModel())
                    .setSpec(productPickItem.getSpec()).setKind(productPickItem.getKind()).setUnitOfMeasure(productPickItem.getUnitOfMeasure())
                    .setPurchasePrice(productPickItem.getPurchasePrice()).setQuantity(productPickItem.getQuantity())
                    .setNoIncludTax(item.getPurchasePrice().multiply( item.getQuantity()))
                    .setIncludTax(item.getPurchasePrice().multiply(item.getQuantity()));
                   productPickItemMapper.insert(item);
        }
        return productPickMapper.insert(productPick);
    }



    @Override
    public ProductPick findProductPickById(Long id) {
        return productPickMapper.selectById(id);
    }



    @Override
    public Integer removeProductPick(List<Long> ids) {
        return productPickMapper.deleteBatchIds(ids);
    }



    @Override
    public Integer updateProductPick(ProductPick productPick) {
        ProductPick productPickDo = productPickMapper.selectById(productPick.getId());
        productPickItemMapper.delete(new LambdaQueryWrapperX<ProductPickItem>().eq(ProductPickItem::getPickNumber,productPickDo.getNumber()));
        for(ProductPickItem productPickItem: productPick.getProductPickItemList()){
            ProductPickItem item = new ProductPickItem();
            item.setPickNumber(productPick.getNumber()).setItemCode(productPickItem.getItemCode()).setItemName(productPickItem.getItemName()).setModel(productPickItem.getModel())
                    .setSpec(productPickItem.getSpec()).setKind(productPickItem.getKind()).setUnitOfMeasure(productPickItem.getUnitOfMeasure())
                    .setPurchasePrice(productPickItem.getPurchasePrice()).setQuantity(productPickItem.getQuantity())
                    .setNoIncludTax(item.getPurchasePrice().multiply( item.getQuantity()))
                    .setIncludTax(item.getPurchasePrice().multiply(item.getQuantity()));
            productPickItemMapper.insert(item);
        }
        return productPickMapper.updateById(productPick);
    }

    @Override
    public List<ProductPick> listAll() {
        return productPickMapper.selectList();
    }

    @Override
    public List<ProductPickExportVo> listData() {
        return BeanCopyUtil.copyListProperties(this.listAll(), ProductPickExportVo::new);
    }

    @Override
    public List<ProductPickAllExportVo> listAllDataByIds(List<Integer> ids) {
        return productPickMapper.exportAllByIds(ids);
    }
}
