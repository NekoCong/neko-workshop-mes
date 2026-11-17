package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReturnsQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.Returns;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReturnExcelVo;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/18 16:41
 */
public interface ReturnsService {

    Integer saveReturns(Returns returns);


    Returns findReturnById(Long id);

    Integer removeReturns(List<Long> ids);

    Integer updateReturns(Returns returns);

    PageResult<Returns> findPage(ReturnsQueryVo returnsQueryVo);

    List<ReturnExcelVo> exportData();
}
