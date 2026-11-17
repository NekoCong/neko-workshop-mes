package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.module.mes.dal.dataobject.order.SemiLabelItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/8/9 17:06
 */
public interface SemiLabelItemService {

    List<SemiLabelItem> selectSemiLabelItemByNumber(String semiNumber);

    Integer deleteSemiLabelItem(String semiNumber);
}
