package io.neko.workshop.module.mes.controller.admin.wm;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseAreaQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseArea;
import io.neko.workshop.module.mes.service.wm.WareHouseAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.AREA_EXIST;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.WAREHOUSE_EXIST;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/13 15:33
 */
@Tag(name = "管理后台 - 库区")
@RestController
@RequestMapping("/mes/wm/warehouse/area")
@RequiredArgsConstructor
public class WareHouseAreaController {

    private final WareHouseAreaService wareHouseAreaService;




    @GetMapping("/list")
    @Operation(summary = "库区列表")
    public CommonResult<PageResult<WareHouseArea>>list(WareHouseAreaQueryVo wareHouseAreaQueryVo){
        return success(wareHouseAreaService.findPage(wareHouseAreaQueryVo));
    }



    @PostMapping("/save")
    @Operation(summary = "新增库区")
     public CommonResult<Integer> save(@RequestBody WareHouseArea wareHouseArea){
        if(wareHouseAreaService.findWareHouseAreaByName(wareHouseArea.getAreaName()) != null){
            throw exception(AREA_EXIST);
        }
         return success(wareHouseAreaService.saveWareHouseArea(wareHouseArea)).setMsg("新增成功");
     }



    @DeleteMapping("/batch")
    @Operation(summary = "删除库区")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return success(wareHouseAreaService.removeWareHouseAreaByIds(ids)).setMsg("删除成功");
    }




    @PutMapping("/update")
    @Operation(summary = "修改库区")
    public CommonResult<Integer> update(@RequestBody WareHouseArea wareHouseArea){
        return success(wareHouseAreaService.updateWareHouseArea(wareHouseArea)).setMsg("修改成功");
    }


    @GetMapping("/find/{id}")
    @Operation(summary = "库区详情")
    public CommonResult<WareHouseArea> findWareHouseById(@PathVariable Long id){
        return success(wareHouseAreaService.findWareHouseAreaById(id));
    }


}
