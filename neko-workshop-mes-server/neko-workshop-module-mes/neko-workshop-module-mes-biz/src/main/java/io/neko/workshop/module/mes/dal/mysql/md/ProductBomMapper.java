package io.neko.workshop.module.mes.dal.mysql.md;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ProductBomQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.ProductBom;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品BOM关系 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface ProductBomMapper extends BaseMapperX<ProductBom> {

    /**
     * 产品BOM条件分页查询
     * @param reqVO
     * @return PageResult<ProductBom>
     */
    default PageResult<ProductBom> selectPage(ProductBomQueryVo reqVO) {
        LambdaQueryWrapperX<ProductBom> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotNull(reqVO.getItemId())){
            queryWrapper.eq(ProductBom::getItemId,reqVO.getItemId());
        }
        return selectPage(reqVO,queryWrapper);
    }


}
