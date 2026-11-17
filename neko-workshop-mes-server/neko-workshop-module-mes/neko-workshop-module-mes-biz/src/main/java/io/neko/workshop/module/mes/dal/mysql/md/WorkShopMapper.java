package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.WorkShopQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.WorkShop;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车间 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface WorkShopMapper extends BaseMapperX<WorkShop> {

    /**
     * 车间条件分页查询
     * @param reqVO
     * @return  PageResult<Workshop>
     */
    default PageResult<WorkShop> selectPage(WorkShopQueryVo reqVO) {
        LambdaQueryWrapperX<WorkShop> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(reqVO.getWorkshopCode())){
            queryWrapper.eq(WorkShop::getWorkshopCode,reqVO.getWorkshopCode());
        }
        if(StringUtils.isNotBlank(reqVO.getWorkshopName())){
            queryWrapper.like(WorkShop::getWorkshopName,reqVO.getWorkshopName());
        }
        if(StringUtils.isBlank(reqVO.getWorkshopCode()) && StringUtils.isBlank(reqVO.getWorkshopName())){
            queryWrapper.isNotNull(WorkShop::getId);
        }
        return  selectPage(reqVO,queryWrapper);
    }



    /**
     * 车间条件分页查询
     * @param workshopPage
     * @return  PageResult<Workshop>
     */
   default PageResult<WorkShop> listWorkshopPage(WorkShopQueryVo workshopPage){
       LambdaQueryWrapperX<WorkShop> queryWrapper = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(workshopPage.getWorkshopCode())){
           queryWrapper.eq(WorkShop::getWorkshopCode,workshopPage.getWorkshopCode());
       }
       if(StringUtils.isNotBlank(workshopPage.getWorkshopName())){
           queryWrapper.like(WorkShop::getWorkshopName,workshopPage.getWorkshopName());
       }
       if(StringUtils.isNotBlank(workshopPage.getCharge())){
           queryWrapper.eq(WorkShop::getCharge,workshopPage.getCharge());
       }

       if(StringUtils.isBlank(workshopPage.getWorkshopCode()) && StringUtils.isBlank(workshopPage.getWorkshopName())
       && StringUtils.isBlank(workshopPage.getCharge())){
           queryWrapper.isNotNull(WorkShop::getId);
       }
       return selectPage(workshopPage,queryWrapper);
   }


    /**
     * 根据编号和名称查询车间信息
     * @param workshopCode
     * @param workshopName
     * @return Workshop
     */
   default WorkShop selectByCodeAndName(String workshopCode, String workshopName){
       LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(WorkShop::getWorkshopCode,workshopCode);
       queryWrapperX.eq(WorkShop::getWorkshopName,workshopName);
       return  selectOne(queryWrapperX);
   }

    /**
     * 根据车间id查询车间信息
     * @param workshopId
     * @return Workshop
     */
  default WorkShop selectByShopId(Long workshopId){
      LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
      queryWrapperX.eq(WorkShop::getId,workshopId);
      return  selectOne(queryWrapperX);
  }

   default WorkShop findWorkshopByName(String workshop){
       LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(WorkShop::getWorkshopName,workshop);
       return selectOne(queryWrapperX);
   }
}
