package io.neko.workshop.module.mes.controller.admin.order;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.OtherInboundQueryVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.ReceiptItemQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.*;
import io.neko.workshop.module.mes.dal.dataobject.wh.InboundRecBillItem;
import io.neko.workshop.module.mes.service.order.OtherInboundItemService;
import io.neko.workshop.module.mes.service.order.OtherInboundService;
import io.neko.workshop.module.mes.service.wh.InboundRecBillItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;

/**
 * 其他采购入库Controller
 * @author 灵喵工坊
 */
@Tag(name = "管理后台 - 其他采购入库")
@RestController
@RequestMapping("/mes/order/other/inbound")
@RequiredArgsConstructor
public class OtherInboundController {

    private final OtherInboundService otherInboundService;
    private final OtherInboundItemService otherInboundItemService;
    private final InboundRecBillItemService inboundRecBillItemService;

    @GetMapping("/list")
    @Operation(summary = "其他采购入库列表")
    public CommonResult<PageResult<OtherInbound>> list(OtherInboundQueryVo otherInboundQueryVo){
        /*List deliveryDate = otherInboundQueryVo.getDeliveryDate();
        if (deliveryDate.size() != 0){
            otherInboundQueryVo.setStartTime(deliveryDate.get(0).toString());
            otherInboundQueryVo.setEndTime(deliveryDate.get(1).toString());
        }*/
        PageResult<OtherInbound> pageResult = otherInboundService.findPage(otherInboundQueryVo);
        BigDecimal totalQuantity = BigDecimal.ZERO;
        for(OtherInbound otherInbound :pageResult.getList()){
            List<OtherInboundItem> otherInboundItemList =   otherInboundItemService.findItemByNumber(otherInbound.getInNumber());
            for(OtherInboundItem otherInboundItem:otherInboundItemList){
                totalQuantity = totalQuantity.add(otherInboundItem.getAmount());
            }
            otherInbound.setQuantity(totalQuantity);
        }
        return success(pageResult);
    }


    @GetMapping("/find/{id}")
    @Operation(summary = "查询详情")
    public CommonResult<OtherInbound> findOtherInboundById(@PathVariable Long id){
        return success(otherInboundService.findOtherInboundById(id));
    }


    @PostMapping("/save")
    @Operation(summary = "新增")
    public CommonResult<Integer> save(@RequestBody OtherInbound otherInbound){
        return success(otherInboundService.saveOtherInbound(otherInbound)).setMsg("新增成功");
    }


    @PostMapping("/update")
    @Operation(summary = "修改")
    public CommonResult<Integer> update(@RequestBody OtherInbound otherInbound){
        return success(otherInboundService.updateOtherInbound(otherInbound)).setMsg("修改成功");
    }


    @DeleteMapping("/batch")
    @Operation(summary = "删除")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        for(Long id:ids){
            OtherInbound otherInbound = otherInboundService.findOtherInboundById(id);
            List<OtherInboundItem> itemList = otherInboundItemService.findItemByNumber(otherInbound.getInNumber());
            for(OtherInboundItem otherInboundItem :itemList){
                InboundRecBillItem inboundRecBillItem =  inboundRecBillItemService.findInboundRecBillItem(otherInboundItem.getItemCode(),otherInboundItem.getRecNumber());
                inboundRecBillItem.setStatus("0");
                inboundRecBillItemService.updateinboundRecBillItem(inboundRecBillItem);
            }
        }
        return success(otherInboundService.removeOtherInboundItem(ids)).setMsg("删除成功");
    }
}
