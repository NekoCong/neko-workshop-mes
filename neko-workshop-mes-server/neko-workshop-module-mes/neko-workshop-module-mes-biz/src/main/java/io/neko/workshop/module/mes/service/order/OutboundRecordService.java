package io.neko.workshop.module.mes.service.order;


import io.neko.workshop.module.mes.dal.dataobject.order.OutboundRecord;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/19 15:15
 */
public interface OutboundRecordService {


    String saveOutboundCount(OutboundRecord outboundCount);


    List<OutboundRecord> findByOutboundNum(String number);

    Integer removeByOutboundNum(String number);

    Boolean returnGood(String qrCode);

}
