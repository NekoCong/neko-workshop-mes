package io.neko.workshop.module.bpm.controller.admin.oa;

import io.neko.workshop.module.bpm.controller.admin.oa.vo.BpmOALeaveCreateReqVO;
import io.neko.workshop.module.bpm.controller.admin.oa.vo.BpmOALeavePageReqVO;
import io.neko.workshop.module.bpm.controller.admin.oa.vo.BpmOALeaveRespVO;
import io.neko.workshop.module.bpm.convert.oa.BpmOALeaveConvert;
import io.neko.workshop.module.bpm.dal.dataobject.oa.BpmOALeaveDO;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.bpm.service.oa.BpmOALeaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import lombok.RequiredArgsConstructor;

/**
 * OA 请假申请 Controller，用于演示自己存储数据，接入工作流的例子
 *
 * @author jason
 * @author 灵喵工坊
 */
@Tag(name = "管理后台 - OA 请假申请")
@RestController
@RequestMapping("/bpm/oa/leave")
@Validated
@RequiredArgsConstructor
public class BpmOALeaveController {

    private final BpmOALeaveService leaveService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建请求申请")
    public CommonResult<Long> createLeave(@Valid @RequestBody BpmOALeaveCreateReqVO createReqVO) {
        return success(leaveService.createLeave(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得请假申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<BpmOALeaveRespVO> getLeave(@RequestParam("id") Long id) {
        BpmOALeaveDO leave = leaveService.getLeave(id);
        return success(BpmOALeaveConvert.INSTANCE.convert(leave));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得请假申请分页")
    public CommonResult<PageResult<BpmOALeaveRespVO>> getLeavePage(@Valid BpmOALeavePageReqVO pageVO) {
        PageResult<BpmOALeaveDO> pageResult = leaveService.getLeavePage(getLoginUserId(), pageVO);
        return success(BpmOALeaveConvert.INSTANCE.convertPage(pageResult));
    }

}
