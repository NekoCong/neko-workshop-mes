package io.neko.workshop.module.mes.dal.mysql.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.order.vo.PurchaseOrderReturnQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrder;
import io.neko.workshop.module.mes.dal.dataobject.order.PurchaseOrderReturn;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 灵喵工坊
 * @time 2023/8/15 11:55
 */
@Mapper
public interface PurchaseOrderReturnMapper extends BaseMapperX<PurchaseOrderReturn> {

    default PageResult<PurchaseOrderReturn> findPage(PurchaseOrderReturnQueryVo queryVo){
        LambdaQueryWrapperX<PurchaseOrderReturn> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(queryVo.getNumber())){
            queryWrapperX.eq(PurchaseOrderReturn::getNumber,queryVo.getNumber());
        }

        if(StringUtils.isNotBlank(queryVo.getPoNumber())){
            queryWrapperX.eq(PurchaseOrderReturn::getPoNumber,queryVo.getPoNumber());
        }

        if(StringUtils.isNotBlank(queryVo.getCreateTime())){
            LocalDate requestDate = LocalDate.parse(queryVo.getCreateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            queryWrapperX.ge(PurchaseOrderReturn::getReturnDate, requestDate);
        }
        if(StringUtils.isNotBlank(queryVo.getEndTime())){
            LocalDate requestDate = LocalDate.parse(queryVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            queryWrapperX.le(PurchaseOrderReturn::getReturnDate, requestDate);
        }

        if(StringUtils.isNotBlank(queryVo.getVendorName())){
            queryWrapperX.eq(PurchaseOrderReturn::getVendorName,queryVo.getVendorName());
        }
        if(StringUtils.isBlank(queryVo.getNumber()) && StringUtils.isBlank(queryVo.getVendorName()) && StringUtils.isBlank(queryVo.getCreateTime())
                && StringUtils.isNotBlank(queryVo.getEndTime()) && StringUtils.isNotBlank(queryVo.getPoNumber())){
            queryWrapperX.isNotNull(PurchaseOrderReturn::getId);
        }
        return  selectPage(queryVo,queryWrapperX);
    }
}
