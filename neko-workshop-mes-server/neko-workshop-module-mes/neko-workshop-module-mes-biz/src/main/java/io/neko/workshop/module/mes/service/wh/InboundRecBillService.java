package io.neko.workshop.module.mes.service.wh;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillQueryVo;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillVo;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBill;

import java.text.ParseException;
import java.util.List;

/**
 * 到货通知Service
 *
 * @author 灵喵工坊MES
 */
public interface InboundRecBillService {

    PageResult<InboundRecBill> findPage(InboundRecBillQueryVo inboundRecBillQueryVo);

    void insertInboundRecBill(InboundRecBillVo inboundRecBillVo) throws ParseException;

    void insertInboundRecBillAll(InboundRecBillVo inboundRecBillVo);

    Integer updateStatus(String number,String status);

    InboundRecBill selectByNumber(String number);
}
