package io.neko.workshop.module.mes.dal.mysql.dv;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.Machinery;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;



/**
 * 设备 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface MachineryMapper extends BaseMapperX<Machinery> {


    /**
     * 设备条件分页查询
     * @param reqVO
     * @return
     */
    default PageResult<Machinery> selectPage(MachineryQueryVo reqVO) {
        LambdaQueryWrapperX<Machinery> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(reqVO.getMachineryCode())){
            queryWrapper.eq(Machinery::getMachineryCode,reqVO.getMachineryCode());
        }
        if(StringUtils.isNotBlank(reqVO.getMachineryName())){
            queryWrapper.like(Machinery::getMachineryName,reqVO.getMachineryName());
        }
        if(StringUtils.isNotNull(reqVO.getMachineryTypeId())){
            queryWrapper.eq(Machinery::getMachineryTypeId,reqVO.getMachineryTypeId());
        }
        if(StringUtils.isNotBlank(reqVO.getStatus())){
            queryWrapper.eq(Machinery::getStatus,reqVO.getStatus());
        }
        if(StringUtils.isBlank(reqVO.getMachineryCode()) && StringUtils.isBlank(reqVO.getMachineryName()) && StringUtils.isNull(reqVO.getMachineryTypeId())
        && StringUtils.isBlank(reqVO.getStatus())){
            queryWrapper.isNotNull(Machinery::getId);
        }
        return selectPage(reqVO,queryWrapper);
    }





}
