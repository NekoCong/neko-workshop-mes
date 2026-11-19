package io.neko.workshop.module.mes.controller.admin.pro;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import io.neko.workshop.module.mes.common.UserConstants;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedbackQueryVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.ProWorkorderVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.WorkorderExcelVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.WorkOrderQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Item;
import io.neko.workshop.module.mes.dal.dataobject.md.ProductBom;
import io.neko.workshop.module.mes.dal.dataobject.pro.*;
import io.neko.workshop.module.mes.service.md.ItemService;
import io.neko.workshop.module.mes.service.md.ProductBomService;
import io.neko.workshop.module.mes.service.pro.*;
import io.neko.workshop.module.mes.utils.StringUtils;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.common.pojo.CommonResult;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.*;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Tag(name = "管理后台 - 生产订单")
@RestController
@RequestMapping("/mes/pro/workorder")
@RequiredArgsConstructor
public class WorkOrderController {


    private final WorkOrderService workorderService;

    private final ItemService itemService;

    private final ProductBomService productBomService;

    private final WorkOrderBomService workorderBomService;

    private final TaskService taskService;

    private final FeedbackService feedbackService;

    private final FeedbackEquipmentService feedbackEquipmentService;

    private final FeedbackEquStatusService feedbackEquStatusService;

    private final FeedbackHoursService feedbackHoursService;

    private final RouteProductService routeProductService;

    private final RouteService routeService;



    @Operation(summary = " 根据产品编号查询产品的工艺以及工序信息")
    @GetMapping("/findRoute/{itemCode}")
    public CommonResult<Map<String,Object>> findRouteByItemCode(@PathVariable String itemCode){
        Map<String,Object> map =  workorderService.findRouteProcess(itemCode);
        return  success(map);
    }







    @Operation(summary = " 查询所有的订单数量")
    @GetMapping("/findWorkerOrderAll")
    public  CommonResult<HashMap<String,Object>>  workOrderAll(){
      List<WorkOrder> list =  workorderService.findWorkerOrderAll();
        int totalQuantity = 0;
        //对工时进行遍历相加
        for (WorkOrder workHours : list) {
            BigDecimal quantity = workHours.getQuantity();
            totalQuantity += quantity.intValue();
        }
        HashMap<String, Object> map = new HashMap<>(200);
        map.put("totalQuentity",totalQuantity);
        map.put("name","生产订单总数");
        return  success(map);
    }




    @Operation(summary = "同步订单列表")
    @GetMapping("/synchronizeOrdersList")
    public CommonResult<PageResult<WorkOrder>>  synchronizeOrdersList(ProWorkorderVo proWorkOrderVo){
     List<WorkOrder> list =   workorderService.synchronizeOrdersList(proWorkOrderVo);
        PageResult<WorkOrder> pageResult = new PageResult<>();
        pageResult.setList(list);
        pageResult.setTotal((long) list.size());
     return  success(pageResult);
    }






    @GetMapping("/list")
    @Operation(summary = "生产订单条件分页")
    @PreAuthorize("@ss.hasPermission('pro:workorder:list')")
    public CommonResult<PageResult<WorkOrder>> list(WorkOrderQueryVo workOrderPage) {
        PageResult<WorkOrder> pageResult = workorderService.getWorkOrderPage(workOrderPage);
        List<WorkOrder> workOrderList = pageResult.getList();
        for(WorkOrder list:workOrderList){
            // 日期字段可能为空，截取前需防空，避免列表接口 500
            String requestDate = list.getRequestDate();
            if (StringUtils.isNotNull(requestDate) && requestDate.length() >= 10) {
                list.setRequestDate(requestDate.substring(0, 10));
            }
            String produceDate = list.getProduceDate();
            if (StringUtils.isNotNull(produceDate) && produceDate.length() >= 10) {
                list.setProduceDate(produceDate.substring(0, 10));
            }
        }
        workOrderList.sort(Comparator.comparing(this::getOrderStatusValue));
        return success(pageResult);
    }






    /**
     *  根据状态进行排序的方法
     */
    private int getOrderStatusValue(WorkOrder workorder) {
        String status = workorder.getStatus();
        if ("NOSCHEDUL".equals(status)) {
            return 1;
        } else if ("SCHEDUL".equals(status)) {
            return 2;
        } else if ("COMPLETED".equals(status)) {
            return 3;
        } else {
            return Integer.MAX_VALUE;
        }
    }



    @PreAuthorize("@ss.hasPermission('pro:workorder:synchronizeOrders')")
    @GetMapping("/synchronizeOrders")
    @Operation(summary = "同步生产订单列表显示")
    public CommonResult<PageResult<ProWorkorderVo>> synchronizeProductOrders(ProWorkorderVo  proWorkorderVo){
        List<ProWorkorderVo> synchronizeList=  workorderService.synchronizeOrders(proWorkorderVo);
        PageResult<ProWorkorderVo> pageResult = new PageResult<>();
        pageResult.setList(synchronizeList);
        pageResult.setTotal((long) synchronizeList.size());
        return success(pageResult);
    }








    @GetMapping("/orderSynchronization")
    @Operation(summary = "同步第三方生产订单")
    public CommonResult<Integer> orderSynchronization() throws IOException, ParseException {
        workorderService.syncOrders();
        return success(200).setMsg("数据同步成功");
    }







    //@Scheduled(cron = "*/59 * * * * *")
    @GetMapping("/synchronous")
    public CommonResult<Integer> synchronous() throws IOException {
        String url = "http://localhost:18080/admin-api/mes/pro/workorder/orderSynchronization?tenant-id=1";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + "3dacd7b9cd5244ef8b483c81614da5cf")
                .GET()
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException(e);
        }
        return success(200).setMsg("数据同步成功");
    }









    @GetMapping("/get/{id}")
    @Operation(summary = "生产订单详情")
    @PreAuthorize("@ss.hasPermission('pro:workorder:get')")
    public CommonResult<WorkOrder>  getWorkOrder(@PathVariable Long id) {
        return success(workorderService.getWorkorder(id));
    }



    @GetMapping("/findFeedBackByWorkOrderCode")
    public  CommonResult<PageResult<Feedback>> orderFeedbackList(FeedbackQueryVo workOrder){
        PageResult<Feedback> pageResult =  feedbackService.findFeedBackByWorkOrderCode(workOrder);
        List<Feedback> feedbacks = pageResult.getList();
        feedbacks.sort(Comparator.comparing(this::getFeedBackStatus));
        return success(pageResult);
    }


    /**
     *  根据订单状态进行排序  FINISHED状态在最前  NoSTARTED状态在FINISHED之后
     */
    private int getFeedBackStatus(Feedback feedback) {
        String status = feedback.getStatus();
        if ("FINISHED".equals(status)) {
            return 1;
        } else if ("NoSTARTED".equals(status)) {
            return 2;
        }  else {
            return Integer.MAX_VALUE;
        }
    }







    @PostMapping("/save")
    @Operation(summary = "新增生产订单")
    @PreAuthorize("@ss.hasPermission('pro:workorder:save')")
    public CommonResult<Long>  save( @RequestBody WorkOrder workOrderDO)  throws  Exception{
        if(UserConstants.NOT_UNIQUE.equals(workorderService.checkWorkorderCodeUnique(workOrderDO))){
            throw  exception(WORKORDER_CODE_EXIST);
        }
        Item mdItem =  itemService.selectMdItemByItemCode(workOrderDO.getProductCode());
        if(StringUtils.isNull(mdItem)){
            throw  exception(ITEM_CODE_ERROR);
        }
        if(workOrderDO.getParentId()==null || workOrderDO.getParentId()==0){
            workOrderDO.setAncestors("0");
        }
        workOrderDO.setProductId(mdItem.getId());
        workOrderDO.setRequestDate(workOrderDO.getRequestDate());

        workorderService.saveWorkOrder(workOrderDO);
        Long workOrderId = workOrderDO.getId();
        generateBomLine(workOrderId);
        return success(workOrderId).setMsg("新增成功");
    }




    /**
     * 根据生产工单中的产品生成BOM物料行
     */
    private void generateBomLine(Long workorderId){
        //先根据ID找到对应的产品
        WorkOrder workorder = workorderService.selectProWorkorderByWorkorderId(workorderId);
        //根据产品找到BOM组成
        List<ProductBom> productBom = productBomService.selectMdProductBomListByProductId(workorder.getProductId());
        //生成BOM数据
        BigDecimal orderQuantity = workorder.getQuantity();
        if(CollUtil.isNotEmpty(productBom)){
            for (ProductBom bom:productBom
            ) {
                WorkOrderBom workorderBom = new WorkOrderBom();
                workorderBom.setWorkorderId(workorderId);
                workorderBom.setItemId(bom.getBomItemId());
                workorderBom.setItemCode(bom.getBomItemCode());
                workorderBom.setItemName(bom.getBomItemName());
                workorderBom.setItemSpc(bom.getBomItemSpec());
                workorderBom.setItemOrProduct(bom.getItemOrProduct());
                workorderBom.setUnitOfMeasure(bom.getUnitOfMeasure());
                workorderBom.setQuantity(orderQuantity.multiply(bom.getQuantity()));
                workorderBomService.insertProWorkorderBom(workorderBom);
            }
        }
    }




    @PutMapping("/update")
    @Operation(summary = "修改生产订单")
    @PreAuthorize("@ss.hasPermission('pro:workorder:update')")
    public CommonResult<Integer>  updateWorkOrder( @RequestBody WorkOrder workOrder) {
        WorkOrder workorder = workorderService.selectProWorkorderByWorkorderId(workOrder.getId());
        int ret =workorderService.updateProWorkorder(workOrder);
        if(ret >0){
            if(workorder.getProductId().longValue() != workOrder.getProductId().longValue() ||
                workorder.getQuantity().compareTo(workOrder.getQuantity())!=0){
                removeBomLine(workOrder.getId());
                generateBomLine(workOrder.getId());
            }
        }
        return success(ret).setMsg("修改成功");
    }





    /**
     *  删除当前工单下所有BOM组成
     */
    private void removeBomLine(Long workorderId){
        WorkOrderBom param = new WorkOrderBom();
        param.setWorkorderId(workorderId);
        workorderBomService.removeWorkOrderBomByWorkOrderId(workorderId);
    }




    @DeleteMapping("/delete")
    @Operation(summary = "删除生产订单")
    @PreAuthorize("@ss.hasPermission('pro:workorder:delete')")
    public  CommonResult<Integer>  batch(@RequestBody List<Long>  workOrderIds) {
        for (Long id:workOrderIds) {
            WorkOrder workorder = workorderService.selectProWorkorderByWorkorderId(id);
            removeBomLine(id);
            taskService.deleteByWorkOrderId(id);
            List<Feedback> feedbackList = feedbackService.findByOrderCode(workorder.getWorkorderCode());
            if(feedbackList.size()>0) {
                for(Feedback feedback:feedbackList) {
                    FeedbackEquipment feedbackEquipment = feedbackEquipmentService.findFeedbackEquipmentById(feedback.getId());
                    //删除设备信息
                    feedbackEquipmentService.removeFeedBackEquipmentById(feedbackEquipment.getId());
                    //删除设备状态信息
                    feedbackEquStatusService.removeFeedbackEquStatusById(feedbackEquipment.getId());
                    //删除报工表
                    feedbackService.removeFeedBackById(feedback.getId());
                    //删除工时
                    feedbackHoursService.removeFeedBackHoursById(feedback.getId());
                }
            }
        }
        int i=  workorderService.removeWorkOrderByWorkOrderIds(workOrderIds);
        if(i>0){
            return success(i).setMsg("删除成功");
        }
            return success(i).setMsg("删除失败");
    }






    /**
     *  获取当前工单的物料需求清单
     */
    @GetMapping("/listItems")
    public CommonResult<List<ProductBom>> listItems(WorkOrder proWorkorder)
    {
        // 产品BOM关系对象 md_product_bom
        List<ProductBom> result = new ArrayList<ProductBom>();
        // 生产工单BOM组成对象
        WorkOrderBom param = new WorkOrderBom();
        param.setWorkorderId(proWorkorder.getId());
        List<WorkOrderBom> workorderBomList = workorderBomService.selectProWorkorderBomList(param);
        if(!CollectionUtils.isEmpty(workorderBomList)){
            for ( WorkOrderBom bom: workorderBomList) {
                Long itemId = bom.getItemId();
                List<ProductBom> productBom =   productBomService.selectMdProductBomByItemId(itemId);
                result.addAll(productBom);
            }
        }
        return success(result);
    }



    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('pro:workorder:export')")
    @Operation(summary = "导出生产订单")
    public void export(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("生产订单", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
            SimpleColumnWidthStyleStrategy styleStrategy = new SimpleColumnWidthStyleStrategy(20);
            EasyExcel.write(response.getOutputStream(), WorkorderExcelVo.class).registerWriteHandler(styleStrategy).sheet("生产订单").doWrite(workorderService.listData());
        } catch (IOException e) {
            throw exception(EXPORT_DATA_ERROR);
        }
    }



}
