package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.ModelQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Model;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 型号Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface ModelMapper extends BaseMapperX<Model> {

    default PageResult<Model> findPage(ModelQueryVo modelQueryVo) {
        LambdaQueryWrapperX<Model> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(modelQueryVo.getName())){
            queryWrapperX.like(Model::getName, modelQueryVo.getName());
        }
        if(StringUtils.isNotBlank(modelQueryVo.getNumber())){
            queryWrapperX.like(Model::getNumber, modelQueryVo.getNumber());
        }

        if(StringUtils.isBlank(modelQueryVo.getName()) && StringUtils.isBlank(modelQueryVo.getNumber()) && StringUtils.isBlank(modelQueryVo.getIsProcure())){
            queryWrapperX.isNotNull(Model::getId);
        }
        return selectPage(modelQueryVo, queryWrapperX);
    }

}
