package io.neko.workshop.module.mes.service.impl.md;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ProductBomQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.ProductBom;
import io.neko.workshop.module.mes.dal.mysql.md.ProductBomMapper;
import io.neko.workshop.module.mes.service.md.ProductBomService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import io.neko.workshop.framework.common.pojo.PageResult;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.PRODUCT_BOM_NOT_EXISTS;
import lombok.RequiredArgsConstructor;

/**
 * 产品BOM关系 Service 实现类
 *
 * @author 灵喵工坊MES
 */
@Service
@Validated
@RequiredArgsConstructor
public class ProductBomServiceImpl implements ProductBomService {

    private final ProductBomMapper productBomMapper;

    @Override
    public Integer createProductBom(ProductBom productBomDO) {
     productBomDO.setCreateTime(new Date());
     return    productBomMapper.insert(productBomDO);
    }

    @Override
    public Integer updateProductBom(ProductBom productBomDO) {
        productBomDO.setUpdateTime(new Date());
        return productBomMapper.updateById(productBomDO);
    }

    @Override
    public Integer deleteProductBom(List<Long> ids) {

     return  productBomMapper.deleteBatchIds(ids);
    }




    private void validateProductBomExists(Long id) {
        if (productBomMapper.selectById(id) == null) {
            throw exception(PRODUCT_BOM_NOT_EXISTS);
        }
    }

    @Override
    public ProductBom getProductBom(Long id) {
        return productBomMapper.selectById(id);
    }



    @Override
    public PageResult<ProductBom> getProductBomPage(ProductBomQueryVo pageReqVO) {
        return productBomMapper.selectPage(pageReqVO);
    }



    @Override
    public List<ProductBom> selectMdProductBomList(ProductBom param) {
        QueryWrapper<ProductBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("bom_item_id");
        return productBomMapper.selectList(queryWrapper);
    }


    @Override
    public List<ProductBom> selectMdProductBomByItemId(Long itemId) {
        QueryWrapper<ProductBom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id",itemId);
        queryWrapper.orderByAsc("bom_item_id");
        return productBomMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteByItemId(List<Long> ids) {
        LambdaQueryWrapperX<ProductBom> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.in(ProductBom::getItemId, ids);
        productBomMapper.delete(queryWrapperX);
    }



    @Override
    public List<ProductBom> selectByItemId(Long productId) {
        LambdaQueryWrapperX<ProductBom> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProductBom::getBomItemId,productId);
        return productBomMapper.selectList(queryWrapperX);
    }

    @Override
    public List<ProductBom> selectMdProductBomListByProductId(Long productId) {
        LambdaQueryWrapperX<ProductBom> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProductBom::getItemId,productId);
        return productBomMapper.selectList(queryWrapperX);
    }

}
