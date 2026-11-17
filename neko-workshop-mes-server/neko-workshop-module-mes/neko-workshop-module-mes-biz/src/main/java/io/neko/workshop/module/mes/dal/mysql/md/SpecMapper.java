package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.NormQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Spec;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规格Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface SpecMapper extends BaseMapperX<Spec> {

    default PageResult<Spec> findPage(NormQueryVo normQueryVo) {
        LambdaQueryWrapperX<Spec> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(normQueryVo.getModel())){
            queryWrapperX.like(Spec::getModel, normQueryVo.getModel());
        }
        if(StringUtils.isNotBlank(normQueryVo.getFuzzyModel())){
            queryWrapperX.like(Spec::getModel, normQueryVo.getFuzzyModel());
        }
        if(StringUtils.isNotBlank(normQueryVo.getSerial())){
            queryWrapperX.like(Spec::getSerial, normQueryVo.getSerial());
        }
        if(StringUtils.isNotBlank(normQueryVo.getName())){
            queryWrapperX.like(Spec::getName, normQueryVo.getName());
        }
        if(StringUtils.isBlank(normQueryVo.getModel())
                && StringUtils.isBlank(normQueryVo.getFuzzyModel())
                && StringUtils.isBlank(normQueryVo.getSerial())
                && StringUtils.isBlank(normQueryVo.getName())
                && StringUtils.isBlank(normQueryVo.getIsProcure())){
            queryWrapperX.isNotNull(Spec::getId);
        }
        return selectPage(normQueryVo, queryWrapperX);
        
    }

}
