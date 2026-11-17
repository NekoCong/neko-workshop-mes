package io.neko.workshop.module.mes.controller.admin.order;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Client;
import io.neko.workshop.module.mes.dal.dataobject.order.Quote;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteItem;
import io.neko.workshop.module.mes.service.md.ClientService;
import io.neko.workshop.module.mes.service.order.QuoteItemService;
import io.neko.workshop.module.mes.service.order.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.*;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/17 14:12
 */
@Tag(name = "管理后台 - 客户报价单")
@RestController
@RequestMapping("/mes/order/quote")
@RequiredArgsConstructor
public class QuoteController {


    private final QuoteService orderQuoteService;

    private final QuoteItemService orderQuoteItemService;

    private final ClientService clientService;



    @GetMapping("/list")
    @Operation(summary = "报价单列表")
    public CommonResult<PageResult<Quote>> list(QuoteQueryVo orderQuoteQueryVo){
        List createTimes = orderQuoteQueryVo.getCreateTimes();
        if (createTimes.size() != 0){
           orderQuoteQueryVo.setCreateTime(createTimes.get(0).toString());
           orderQuoteQueryVo.setEndTime(createTimes.get(1).toString());
        }
        PageResult<Quote> pageResult = orderQuoteService.findPage(orderQuoteQueryVo);
        for(Quote quote: pageResult.getList()){
            Client client = clientService.findClientByName(quote.getCustomerName());
            if(client != null){
                quote.setCustomerNumber(client.getClientCode());
            }

        }
        return  success(pageResult);
    }



    @PostMapping("/save")
    @Operation(summary = "新增报价单")
    public CommonResult<Integer> save(@RequestBody Quote orderQuote){
      Quote quote =  orderQuoteService.findQuoteByNameLineTypeModel(orderQuote.getCustomerName(),orderQuote.getLineType(),orderQuote.getModel());
      if(quote != null){
          throw exception(QUOTE_ALERADY_EXIST);
      }
        return  success(orderQuoteService.saveOrderQuote(orderQuote)).setMsg("新增成功");
    }



    @DeleteMapping("/batch")
    @Operation(summary = "删除报价单")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        for(Long id:ids){
            orderQuoteItemService.removeByNumber(orderQuoteService.findOrderQuoteById(id).getNumber());
        }
        return  success(orderQuoteService.removeOrderQuote(ids)).setMsg("删除成功");
    }




    @Operation(summary = "修改报价单")
    @PutMapping("/update")
    public CommonResult<Integer> update(@RequestBody Quote orderQuote){
        return  success(orderQuoteService.updateOrderQuote(orderQuote)).setMsg("修改成功");
    }



    @GetMapping("/find/{id}")
    @Operation(summary = "查询报价单详情")
    public CommonResult<Quote> findOrderQuoteById(@PathVariable Long id){
         Quote orderQuote = orderQuoteService.findOrderQuoteById(id);
         List<QuoteItem> orderQuoteItemList =  orderQuoteItemService.findOrderQuoteItemByNUm(orderQuote.getNumber());
         orderQuote.setOrderQuoteItemList(orderQuoteItemList);
         return  success(orderQuote);
    }




    @GetMapping("/listAll")
    @Operation(summary = "查询所有报价单客户名称")
    public CommonResult<List<Quote>> listAll(){
        List<Quote> list = orderQuoteService.listAll();
        Map<String, Quote> customerNameToQuoteMap = new HashMap<>();
        List<Quote> filteredList = new ArrayList<>();
        for(Quote quote: list){
            String customerName = quote.getCustomerName();
            // 如果已经处理过这个客户名称，跳过
            if (customerNameToQuoteMap.containsKey(customerName)) {
                continue;
            }
            // 将第一次遇到的客户名称添加到 Map 中，同时将报价单添加到筛选列表中
            customerNameToQuoteMap.put(customerName, quote);
            Client client = clientService.findClientByName(quote.getCustomerName());
            if(client != null){
                quote.setCustomerNumber(client.getClientCode());
            }
            filteredList.add(quote);
        }
        return  success(filteredList);
    }






    @GetMapping("/export")
    @Operation(summary = "报价单导出")
    public void export(HttpServletResponse response,@RequestBody(required = false) List<Long> ids){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("报价单", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
            SimpleColumnWidthStyleStrategy styleStrategy = new SimpleColumnWidthStyleStrategy(20);
            ArrayList<String> number = new ArrayList<>();
            if (ids == null) {
                EasyExcel.write(response.getOutputStream(), QuoteExportVo.class).registerWriteHandler(styleStrategy).sheet("报价单").doWrite(orderQuoteService.exportAllData());
            }else {
                for(Long id:ids){
                    Quote quote = orderQuoteService.findOrderQuoteById(id);
                    number.add(quote.getNumber());
                }
                EasyExcel.write(response.getOutputStream(), QuoteExportVo.class).registerWriteHandler(styleStrategy).sheet("报价单").doWrite(orderQuoteService.exportData(number));
            }
            } catch (IOException e) {
            throw exception(EXPORT_DATA_ERROR);
        }
    }









}
