package io.neko.workshop.module.mes.dal.mysql.pro;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedBackEquipmentQueryVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedBackEquipmentVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.FeedbackEquipment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 报工设备 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface FeedbackEquipmentMapper  extends BaseMapperX<FeedbackEquipment> {


    /**
     * 根据报工编号查询报工设备信息
     * @param feedbackCode
     * @return FeedbackEquipment
     */
    default FeedbackEquipment selectFeedEquipIdByfeedbackCode(Long feedbackCode){
        LambdaQueryWrapperX<FeedbackEquipment> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(FeedbackEquipment::getFeedbackCode,feedbackCode);
        return selectOne(queryWrapperX);
    }

    /**
     * 根据报工编号查询报工设备信息
     * @param recordId
     * @return FeedbackEquipment
     */
  default FeedbackEquipment findFeedbackEquipmentByrecoreId(Long recordId){
      LambdaQueryWrapperX<FeedbackEquipment> queryWrapperX = new LambdaQueryWrapperX<>();
      queryWrapperX.eq(FeedbackEquipment::getFeedbackCode,recordId);
      queryWrapperX.last("LIMIT 1");
      return  selectOne(queryWrapperX);
  }


    /**
     * 报工设备信息集合
     * @param pageReqVO
     * @return List<FeedBackEquipmentVo>
     */
    List<FeedBackEquipmentVo> list(FeedBackEquipmentQueryVo pageReqVO);


    /**
     *  根据报工编号查询报工设备信息
     * @param id
     * @return FeedbackEquipment
     */
    default   FeedbackEquipment findIdByFeedCode(Long id){
      LambdaQueryWrapperX<FeedbackEquipment> queryWrapperX = new LambdaQueryWrapperX<>();
      queryWrapperX.eq(FeedbackEquipment::getFeedbackCode,id);
      return  selectOne(queryWrapperX);
    }
}
