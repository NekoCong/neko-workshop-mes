package io.neko.workshop.module.mes.service.wh;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillItemQueryVo;
import io.neko.workshop.module.mes.controller.admin.wh.vo.InboundRecBillItemVo;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBillItem;

import java.util.List;

/**
 * 到货通知明细Service
 *
 * @author 灵喵工坊MES
 */
public interface InboundRecBillItemService {

    PageResult<InboundRecBillItem> findPage(InboundRecBillItemQueryVo inboundRecBillItemQueryVo,String flag);

    List<InboundRecBillItemVo> findInboundRecBillItem(String receiptNumber);

    InboundRecBillItem findInboundRecBillItem(String itemCode,String recNumber);

    Integer updateinboundRecBillItem(InboundRecBillItem inboundRecBillItem);

    Integer updateState(String itemCode,String recNumber,String state);
}
