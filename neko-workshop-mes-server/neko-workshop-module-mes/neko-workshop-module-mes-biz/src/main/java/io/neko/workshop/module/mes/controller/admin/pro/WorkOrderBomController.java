package io.neko.workshop.module.mes.controller.admin.pro;

import io.neko.workshop.module.mes.controller.admin.pro.vo.WorkorderBomQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.WorkOrderBom;
import io.neko.workshop.module.mes.service.pro.WorkOrderBomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.common.pojo.CommonResult;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;



/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Tag(name = "管理后台 - 生产工单BOM组成")
@RestController
@RequestMapping("/mes/pro/workorder/bom")
@RequiredArgsConstructor
public class WorkOrderBomController {

    private final WorkOrderBomService workorderBomService;



    @GetMapping("/list")
    @Operation(summary = "获得生产工单BOM组成分页")
    public CommonResult<PageResult<WorkOrderBom>>  getWorkOrderBomPage( WorkorderBomQueryVo pageVO) {
       PageResult<WorkOrderBom> pageResult = workorderBomService.getWorkorderBomPage(pageVO);
        return success(pageResult);
    }




    @PostMapping("/create")
    @Operation(summary = "创建生产工单BOM组成")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:create')")
    public CommonResult<Integer> createWorkorderBom( @RequestBody WorkOrderBom createReqVO) {
        return success(workorderBomService.createWorkorderBom(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产工单BOM组成")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:update')")
    public CommonResult<Boolean> updateWorkorderBom( @RequestBody WorkOrderBom updateReqVO) {
        workorderBomService.updateWorkorderBom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产工单BOM组成")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:delete')")
    public CommonResult<Boolean> deleteWorkOrderBom(@RequestParam("id") Long id) {
        workorderBomService.deleteWorkorderBom(id);
        return success(true);
    }


}
