package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.OtherStandardItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
public interface OtherStandardItemService {

    Integer saveOtherStandardItem(OtherStandardItem otherStandardItem);

    Integer deleteOtherStandardItem(String otherStandardNumber);

    Integer removeOtherStandardItemByCode(String deviceCode, String otherStandardNumber);

    List<OtherStandardItem> findOtherStandardItemByCode(String otherStandardNumber);


}
