package io.neko.workshop.module.mes.controller.admin.dv;

import io.neko.workshop.module.mes.common.TreeSelect;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.Machinery;
import io.neko.workshop.module.mes.dal.dataobject.dv.MachineryType;
import io.neko.workshop.module.mes.service.dv.MachineryService;
import io.neko.workshop.module.mes.service.dv.MachineryTypeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.*;
import java.util.*;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.common.pojo.CommonResult;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.MACHINERYTYPEHASMACHINERT;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Tag(name = "管理后台 - 设备类型")
@RestController
@RequestMapping("/mes/dv/machineryType")
@RequiredArgsConstructor
public class MachineryTypeController {

    private final MachineryTypeService machineryTypeService;


    private final MachineryService machineryService;




    @GetMapping("/listTree")
    @Operation(summary = "设备分类树形显示")
    public CommonResult<List<TreeSelect>>  selectMachineryTreeList(MachineryType machineryTypeDO){
        List<MachineryType> machineryTypeList = machineryTypeService.findItemTypeList(machineryTypeDO);
        List<TreeSelect> treeSelects =   machineryTypeService.buildTreeSelect(machineryTypeList);
        return success(treeSelects);
    }


    @GetMapping("/list")
    @Operation(summary = "设备类型条件分页查询")
    @PreAuthorize("@ss.hasPermission('dv:machinery:type:list')")
    public CommonResult<PageResult<MachineryType>>  getMachineryTypePage( MachineryTypeQueryVo pageVO) {
        PageResult<MachineryType> pageResult = machineryTypeService.getMachineryTypePage(pageVO);
        return success(pageResult);
    }



    @PostMapping("/save")
    @Operation(summary = "新增设备类型")
    @PreAuthorize("@ss.hasPermission('dv:machinery:type:save')")
    public CommonResult<Integer> save( @RequestBody MachineryType machineryTypeCreateReqVO) {
        return success(machineryTypeService.createMachineryType(machineryTypeCreateReqVO)).setMsg("新增成功");
    }




    @GetMapping("/get/{id}")
    @Operation(summary = "设备类型详情")
    @PreAuthorize("@ss.hasPermission('dv:machinery:type:get')")
    public CommonResult<MachineryType>  getMachineryTypeById(@PathVariable Long id) {
        return success(machineryTypeService.getMachineryType(id));
    }




    @PutMapping("/update")
    @Operation(summary = "修改设备类型")
    @PreAuthorize("@ss.hasPermission('dv:machinery:type:update')")
    public CommonResult<Integer>  updateMachineryType(@RequestBody MachineryType machineryTypeDO){
        return success(machineryTypeService.updateMachineryType(machineryTypeDO)).setMsg("修改成功");
    }




    @DeleteMapping("/{machineryTypeIds}")
    public CommonResult<Integer> remove(@PathVariable Long machineryTypeIds)
    {
     List<Machinery>  machinery =  machineryService.selectByMachineryTypeId(machineryTypeIds);
     if(machinery.size()>0){
         throw exception(MACHINERYTYPEHASMACHINERT);
     }
        return success(machineryTypeService.deleteDvMachineryTypeByMachineryTypeId(machineryTypeIds)).setMsg("删除成功");
    }


}
