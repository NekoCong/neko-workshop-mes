package io.neko.workshop.module.mes.dal.mysql.wh;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveRecord;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBill;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 到货通知明细Mapper
 *
 * @author 灵喵工坊MES
 */
@Mapper
public interface InboundRecBillMapper extends BaseMapperX<InboundRecBill> {

    default PageResult<InboundRecBill> findPage(InboundRecBillQueryVo queryVo){
        LambdaQueryWrapperX<InboundRecBill> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(queryVo.getNumber())){
            queryWrapperX.eq(InboundRecBill::getNumber,queryVo.getNumber());
        }
        if(StringUtils.isNotBlank(queryVo.getReceiptNumber())){
            queryWrapperX.eq(InboundRecBill::getReceiptNumber,queryVo.getReceiptNumber());
        }
        if(StringUtils.isNotBlank(queryVo.getVendorName())){
            queryWrapperX.eq(InboundRecBill::getVendorName,queryVo.getVendorName());
        }
        if(StringUtils.isNotBlank(queryVo.getWareHouse())){
            queryWrapperX.eq(InboundRecBill::getWareHouse,queryVo.getWareHouse());
        }

        if(StringUtils.isBlank(queryVo.getNumber()) &&  StringUtils.isBlank(queryVo.getReceiptNumber())
                && StringUtils.isBlank(queryVo.getVendorName()) && StringUtils.isNotBlank(queryVo.getWareHouse())){
            queryWrapperX.isNotNull(InboundRecBill::getId);
        }
        return  selectPage(queryVo,queryWrapperX);
    }
}
