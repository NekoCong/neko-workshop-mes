package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.*;
import io.neko.workshop.module.mes.dal.dataobject.order.Sale;

import java.util.List;


/**
 * 销售订单Service
 * @author 灵喵工坊MES
 */
public interface SaleService {

    Integer saveOrderSale(Sale orderSale);

    Integer removeOrderSaleByIds(List<Long> ids);

    Integer updateOrderSale(Sale orderSale);

    Sale findOrderSaleById(Long id);

    PageResult<Sale> findPage(SaleQueryVo orderSaleQueryVo);


    List<Sale> findBySaleNumber(String saleNumber);

    List<SaleProgressResVo> findProgressBySaleNumber(SaleProgressResVo saleProgressResVo);


    List<ProductResVo> findProductList(ProductResVo pv);

    List<PrintSaleDateVo> printPurchaseOrder(String saleNumber);

    List<ProductResVo> findProductListAll(ProductResVo pv);

    PageResult<Sale> saleCountPage(SaleCountQueryVo saleCountQueryVo);

    List<SaleCountVo> findOutBound(SaleCountQueryVo saleCountQueryVo);

    Sale findSaleByNUmAndCustomerName(String saleNumber, String customerName);

    Integer updateSale(Sale saleDo);

    List<SaleExcelVo> exportData();

    List<SaleAllExcelVo> exportAllDataByIds(List<Integer> ids);

    //Sale findSaleBySaleNumber(String saleNumber);
}
