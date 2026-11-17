package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.InventoryStatisticsQueryVO;
import io.neko.workshop.module.mes.controller.admin.order.vo.InventoryStatisticsVO;

/**
 * io.neko.workshop.module.mes.service.order
 *
 * @author xx
 * @time 2023/8/17 11:51
 */
public interface InventoryStatisticsService {
    PageResult<InventoryStatisticsVO> InventoryStatistics(String Mode, InventoryStatisticsQueryVO inventoryStatisticsQueryVO);
}
