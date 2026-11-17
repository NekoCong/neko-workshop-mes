package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.UnitMeasureQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.UnitMeasure;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;


/**
 * 单位 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface UnitMeasureMapper extends BaseMapperX<UnitMeasure> {

    /**
     * 单位条件分页查询
     * @param page
     * @return PageResult<UnitMeasure>
     */
    default PageResult<UnitMeasure> selectPage(UnitMeasureQueryVo page) {
        LambdaQueryWrapperX<UnitMeasure> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(page.getId())){
            queryWrapper.eq(UnitMeasure::getId,page.getId());
        }
        if(StringUtils.isNotBlank(page.getMeasureName())){
            queryWrapper.like(UnitMeasure::getMeasureName,page.getMeasureName());
        }
        if(StringUtils.isBlank(page.getId()) && StringUtils.isBlank(page.getMeasureName())){
            queryWrapper.isNotNull(UnitMeasure::getId);
        }
        queryWrapper.orderByAsc(UnitMeasure::getMeasureCode);
        return  selectPage(page,queryWrapper);
    }



}
