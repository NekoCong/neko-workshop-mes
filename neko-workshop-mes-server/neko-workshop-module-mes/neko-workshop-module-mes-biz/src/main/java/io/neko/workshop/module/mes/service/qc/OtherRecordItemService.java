package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecordItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
public interface OtherRecordItemService {

    Integer saveOtherRecordItem(OtherRecordItem otherRecordItem);

    List<OtherRecordItem> findOtherRecordItem(String recordNumber);
}
