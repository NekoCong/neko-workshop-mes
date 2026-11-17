package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.PanHaoQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.PanHao;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 盘号Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface PanHaoMapper extends BaseMapperX<PanHao> {

    default PageResult<PanHao> findPage(PanHaoQueryVo panHaoQueryVo) {
        LambdaQueryWrapperX<PanHao> queryWrapperX = new LambdaQueryWrapperX<>();

        if(StringUtils.isNotBlank(panHaoQueryVo.getNumber())){
            queryWrapperX.like(PanHao::getNumber,panHaoQueryVo.getNumber());
        }
        if(StringUtils.isBlank(panHaoQueryVo.getNumber())){
            queryWrapperX.isNotNull(PanHao::getId);
        }
        return selectPage(panHaoQueryVo, queryWrapperX);
    };

}
