package io.neko.workshop.module.mes.service.order;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.ProductPickAllExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ProductPickExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ProductPickQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.ProductPick;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/21 14:12
 */
public interface ProductPickService {


    PageResult<ProductPick> findPage(ProductPickQueryVo productPickQueryVo);

    Integer saveProductPick(ProductPick productPick);

    ProductPick findProductPickById(Long id);

    Integer removeProductPick(List<Long> ids);

    Integer updateProductPick(ProductPick productPick);

    List<ProductPick> listAll();

    List<ProductPickExportVo> listData();

    List<ProductPickAllExportVo> listAllDataByIds(List<Integer> ids);
}
