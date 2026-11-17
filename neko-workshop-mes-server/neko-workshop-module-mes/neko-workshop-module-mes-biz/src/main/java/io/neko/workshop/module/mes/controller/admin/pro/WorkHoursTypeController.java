package io.neko.workshop.module.mes.controller.admin.pro;


import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.pro.vo.WorkHoursTypeVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.WorkHoursType;
import io.neko.workshop.module.mes.service.pro.WorkHoursTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.WORKHOURS_TYPE;
import lombok.RequiredArgsConstructor;



/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Tag(name = "管理后台 - 临时工时类型")
@RestController
@RequestMapping("/mes/pro/workhours")
@RequiredArgsConstructor
public class WorkHoursTypeController {


    private final WorkHoursTypeService workHoursTypeService;


    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('pro:workhours:list')")
    @Operation(summary = "工时类型条件分页")
    public CommonResult<PageResult<WorkHoursType>> list(WorkHoursTypeVo workHoursTypeVO){
        PageResult<WorkHoursType> list =  workHoursTypeService.list(workHoursTypeVO);
        return  success(list);
    }



    @PostMapping("/save")
    @PreAuthorize("@ss.hasPermission('pro:workhours:save')")
    @Operation(summary = "新增临时工时类型")
    public CommonResult<Integer> save(@RequestBody WorkHoursType workHoursType){
        if(workHoursTypeService.selectByWorkhoursType(workHoursType.getWorkhoursType()) != null){
            throw exception(WORKHOURS_TYPE);
        }
        return success(workHoursTypeService.save(workHoursType)).setMsg("新增成功");
    }


    @DeleteMapping("/batch")
    @PreAuthorize("@ss.hasPermission('pro:workhours:batch')")
    @Operation(summary = "删除临时工时类型")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
      return success(workHoursTypeService.removeByIds(ids)).setMsg("删除成功");
    }


    @GetMapping("/get/{id}")
    @PreAuthorize("@ss.hasPermission('pro:workhours:get')")
    @Operation(summary = "临时工时类型详情")
    public CommonResult<WorkHoursType> getById(@PathVariable Long id){
        return success(workHoursTypeService.findById(id));
    }


    @PutMapping("/update")
    @PreAuthorize("@ss.hasPermission('pro:workhours:update')")
    @Operation(summary = "临时工时类型修改")
    public CommonResult<Integer> update(@RequestBody WorkHoursType workHoursType){
        return success(workHoursTypeService.update(workHoursType)).setMsg("修改成功");
    }





}
