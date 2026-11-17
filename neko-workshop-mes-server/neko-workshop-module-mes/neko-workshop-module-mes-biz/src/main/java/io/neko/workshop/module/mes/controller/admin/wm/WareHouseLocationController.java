package io.neko.workshop.module.mes.controller.admin.wm;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.wm.vo.WareHouseLocationQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.wm.WareHouseLocation;
import io.neko.workshop.module.mes.service.wm.WareHouseLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.LOCATION_EXIST;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/13 15:33
 */
@Tag(name = "管理后台 - 库位")
@RestController
@RequestMapping("/mes/wm/warehouse/location")
@RequiredArgsConstructor
public class WareHouseLocationController {


    private final WareHouseLocationService wareHouseLocationService;



    @GetMapping("/list")
    @Operation(summary = "库位列表")
    public CommonResult<PageResult<WareHouseLocation>> list(WareHouseLocationQueryVo wareHouseLocationQueryVo){
        return success(wareHouseLocationService.findPage(wareHouseLocationQueryVo));
    }



    @PostMapping("/save")
    @Operation(summary = "新增库位")
    public CommonResult<Integer> save(@RequestBody WareHouseLocation wareHouseLocation){
        if(wareHouseLocationService.findWareHouseLocationByName(wareHouseLocation.getLocationName()) != null){
            throw exception(LOCATION_EXIST);
        }
        return success(wareHouseLocationService.saveWareHouseLocation(wareHouseLocation)).setMsg("新增成功");
    }


    @DeleteMapping("/batch")
    @Operation(summary = "删除库位")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return success(wareHouseLocationService.removeWareHouseLocationByIds(ids)).setMsg("删除成功");
    }




    @PutMapping("/update")
    @Operation(summary = "修改库位")
    public CommonResult<Integer> update(@RequestBody WareHouseLocation wareHouseLocation){
        return success(wareHouseLocationService.updateWareHouseLocation(wareHouseLocation)).setMsg("修改成功");
    }



    @GetMapping("/find/{id}")
    @Operation(summary = "库位详情")
    public CommonResult<WareHouseLocation> findWareHouseById(@PathVariable Long id){
        return success(wareHouseLocationService.findWareHouseLocationById(id));
    }


}
