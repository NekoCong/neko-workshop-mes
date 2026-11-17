package io.neko.workshop.module.mes.controller.admin.order;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryExcelVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteModel;
import io.neko.workshop.module.mes.service.order.QuoteModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.EXPORT_DATA_ERROR;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.MODEL_EXIST;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/17 15:06
 */
@Tag(name = "管理后台 - 客户报价单-型号")
@RestController
@RequestMapping("/mes/order/quote/model")
@RequiredArgsConstructor
public class QuoteModelController {



    private final QuoteModelService quoteModelService;



    @GetMapping("/list")
    @Operation(summary = "客户报价单-型号条件分页查询")
    public CommonResult<PageResult<QuoteModel>> list(QuoteModelQueryVo orderQuoteModelQueryVo){
        List createTimes = orderQuoteModelQueryVo.getCreateTimes();
        if (createTimes.size() != 0){
            orderQuoteModelQueryVo.setCreateTime(createTimes.get(0).toString());
            orderQuoteModelQueryVo.setEndTime(createTimes.get(1).toString());
        }
        PageResult<QuoteModel> pageResult = quoteModelService.findPage(orderQuoteModelQueryVo);
        return  success(pageResult);
    }


    @GetMapping("/selectQuoteModel")
    public CommonResult selectQuoteModel(){
        List<QuoteModel> list = quoteModelService.selectQuoteModel();
        return  success(list);
    }


    @PostMapping("/save")
    @Operation(summary = "新增客户报价单-型号")
    public CommonResult<Integer> save(@RequestBody QuoteModel quoteModel){
        if(quoteModelService.findQuoteModelByName(quoteModel.getModel()) != null ){
            throw exception(MODEL_EXIST);
        }
        return  success(quoteModelService.saveOrderQuoteModel(quoteModel)).setMsg("新增成功");
    }



    @DeleteMapping("/batch")
    @Operation(summary = "删除客户报价单-型号")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return  success(quoteModelService.removeOrderQuoteModel(ids)).setMsg("删除成功");
    }



    @PutMapping("/update")
    @Operation(summary = "修改客户报价单-型号")
    public CommonResult<Integer> update(@RequestBody QuoteModel orderQuoteModel){
        return  success(quoteModelService.updateOrderQuoteModel(orderQuoteModel)).setMsg("修改成功");
    }



    @GetMapping("/find/{id}")
    @Operation(summary = "客户报价单-型号详情")
    public CommonResult<QuoteModel> findOrderQuoteModelById(@PathVariable Long id){
        return  success(quoteModelService.findOrderQuoteModelById(id));
    }




    @GetMapping("/export")
    @Operation(summary = "报价单-型号导出")
    public void export(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("报价单型号", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
            SimpleColumnWidthStyleStrategy styleStrategy = new SimpleColumnWidthStyleStrategy(40);
            EasyExcel.write(response.getOutputStream(), QuoteModelExportVo.class).registerWriteHandler(styleStrategy).sheet("报价单型号").doWrite(quoteModelService.exportData());
        } catch (IOException e) {
            throw exception(EXPORT_DATA_ERROR);
        }
    }


}
