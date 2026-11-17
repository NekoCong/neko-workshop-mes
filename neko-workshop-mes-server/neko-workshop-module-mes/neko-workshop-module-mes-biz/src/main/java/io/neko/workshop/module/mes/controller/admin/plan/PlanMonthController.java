package io.neko.workshop.module.mes.controller.admin.plan;

import io.neko.workshop.framework.common.exception.ErrorCode;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.plan.vo.query.SelectPlanMonthQuery;
import io.neko.workshop.module.mes.controller.admin.plan.vo.request.InsertPlanMonthReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.vo.request.UpdatePlanMonthReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.vo.response.SelectPlanMonthResVo;
import io.neko.workshop.module.mes.service.plan.PlanMonthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.*;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.controller.admin.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:34
 */

@Tag(name = "管理后台 - 月计划")
@RestController
@RequestMapping("/mes/plan/month")
@RequiredArgsConstructor
public class PlanMonthController {

    private final PlanMonthService planMonthService;

    @PostMapping("/save")
    @Operation(summary = "保存月计划")
    public CommonResult<Integer> save(@RequestBody @Valid InsertPlanMonthReqVo insertPlanMonthReqVo, BindingResult results) {
        //验空
        if (results.hasErrors())
            throw  exception(new ErrorCode(1000, Objects.requireNonNull(results.getFieldError()).getDefaultMessage()));
        //检查传入日期格式是否匹配 202308
        if (!insertPlanMonthReqVo.getMonth().matches("^[1-9]\\d{2,4}(1[0-2]|0[1-9])$"))
            throw exception(PLAN_MONTH_MONTH_FORMAT_NOT_MATCHED);

        Integer i = planMonthService.savePlanMonth(insertPlanMonthReqVo);
        // 2 代表数据库中已经存在了该月计划编号
        if (i == -1)
            throw exception(PLAN_MONTH_NUMBER_EXIST);

        return success(i).setMsg("新增成功");
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除月计划")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids) {
        //ids列表不可为空
        if (ids.isEmpty())
            throw exception(PLAN_BATCH_NEED_ID);
        return success(planMonthService.removePlanMonthByIds(ids)).setMsg("删除成功");
    }

    @PutMapping("/update")
    @Operation(summary = "修改月计划")
    public CommonResult<Integer> update(@RequestBody UpdatePlanMonthReqVo updatePlanMonthReqVo) {
        Integer i = planMonthService.updatePlanMonthById(updatePlanMonthReqVo);
        //没有找到要修改的id
        if (i == -1)
            throw exception(PLAN_MONTH_ID_NOT_EXIST);
        return success(i).setMsg("修改成功");
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "根据id查看月计划详情，并带出日计划")
    public CommonResult<SelectPlanMonthResVo> selectById(@PathVariable Long id) {
        SelectPlanMonthResVo selectPlanMonthResVo = planMonthService.selectPlanMonthWithPlanDayById(id);
        //避免id不存在时的空指针
        if (selectPlanMonthResVo == null)
            throw exception(PLAN_DAY_ID_NOT_EXIST);
        return success(selectPlanMonthResVo);
    }

    @GetMapping("/findList")
    @Operation(summary = "查看月计划列表，并带出日计划")
    public CommonResult<PageResult<SelectPlanMonthResVo>> selectList(SelectPlanMonthQuery selectPlanMonthQuery) {
        //检查传入日期格式是否匹配 202308
        if (StringUtils.isNotBlank(selectPlanMonthQuery.getMonth()) && !selectPlanMonthQuery.getMonth().matches("^[1-9]\\d{2,4}(1[0-2]|0[1-9])$"))
            throw exception(PLAN_MONTH_MONTH_FORMAT_NOT_MATCHED);
        //分页查询时必须提供pageNo和pageSize (好像不用selectPage就不会报错了)
        if (selectPlanMonthQuery.getPageNo() == null || selectPlanMonthQuery.getPageSize() == null)
            throw exception(PLAN_PAGE_RESULT_NEED_PAGE_INFO);
        return success(planMonthService.selectPlanMonthList(selectPlanMonthQuery));
    }

}
