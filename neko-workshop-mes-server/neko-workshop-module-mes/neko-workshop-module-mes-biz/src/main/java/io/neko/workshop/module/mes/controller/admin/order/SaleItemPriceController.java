package io.neko.workshop.module.mes.controller.admin.order;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.ItemPriceExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.SaleItemPriceQueryVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.SaleItemPriceVo;
import io.neko.workshop.module.mes.dal.dataobject.order.SaleItemPrice;
import io.neko.workshop.module.mes.service.order.SaleItemPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.EXPORT_DATA_ERROR;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/17 15:30
 */
@Tag(name = "管理后台 - 销售订单 - 物料价格")
@RestController
@RequestMapping("/mes/order/sale/price")
@RequiredArgsConstructor
public class SaleItemPriceController {


    private final SaleItemPriceService saleItemPriceService;




    @Operation(summary = "查询最新的铜价铝价")
    @GetMapping("/findPrice")
    public CommonResult<List<SaleItemPriceVo>>  findPrice(String copper){
       List<SaleItemPriceVo> saleItemPriceVo =  saleItemPriceService.findPrice(copper);
       return success(saleItemPriceVo);
    }






    @GetMapping("/list")
    @Operation(summary = "物料价格列表")
    public CommonResult<PageResult<SaleItemPrice>> list(SaleItemPriceQueryVo orderSalePriceQueryVo){
        PageResult<SaleItemPrice> pageResult = saleItemPriceService.findPage(orderSalePriceQueryVo);
        return  success(pageResult);
    }


    @PostMapping("/save")
    @Operation(summary = "新增物料价格")
    public CommonResult<Integer> save(@RequestBody SaleItemPrice orderSalePrice){
        return  success(saleItemPriceService.saveOrderSalePrice(orderSalePrice)).setMsg("新增成功");
    }


    @DeleteMapping("/batch")
    @Operation(summary = "删除物料价格")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return  success(saleItemPriceService.removeOrderSalePrice(ids)).setMsg("删除成功");
    }


    @PutMapping("/update")
    @Operation(summary = "修改物料价格")
    public CommonResult<Integer> update(@RequestBody SaleItemPrice orderSalePrice){
        return  success(saleItemPriceService.updateOrderSalePrice(orderSalePrice)).setMsg("修改成功");
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "物料价格详情")
    public CommonResult<SaleItemPrice> findOrderSalePriceById(@PathVariable Long id){
        return  success(saleItemPriceService.findOrderSalePriceById(id));
    }





    @GetMapping("/export")
    @Operation(summary = "物料价格导出")
    public void export(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("物料价格", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
            SimpleColumnWidthStyleStrategy styleStrategy = new SimpleColumnWidthStyleStrategy(20);
            EasyExcel.write(response.getOutputStream(), ItemPriceExportVo.class).registerWriteHandler(styleStrategy).sheet("物料价格").doWrite(saleItemPriceService.exportData());
        } catch (IOException e) {
            throw exception(EXPORT_DATA_ERROR);
        }
    }





}
