package io.neko.workshop.module.bpm.controller.admin.definition;

import io.neko.workshop.module.bpm.controller.admin.definition.vo.form.*;
import io.neko.workshop.module.bpm.convert.definition.BpmFormConvert;
import io.neko.workshop.module.bpm.dal.dataobject.definition.BpmFormDO;
import io.neko.workshop.module.bpm.service.definition.BpmFormService;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - 动态表单")
@RestController
@RequestMapping("/bpm/form")
@Validated
@RequiredArgsConstructor
public class BpmFormController {

    private final BpmFormService formService;

    @PostMapping("/create")
    @Operation(summary = "创建动态表单")
    @PreAuthorize("@ss.hasPermission('bpm:form:create')")
    public CommonResult<Long> createForm(@Valid @RequestBody BpmFormCreateReqVO createReqVO) {
        return success(formService.createForm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新动态表单")
    @PreAuthorize("@ss.hasPermission('bpm:form:update')")
    public CommonResult<Boolean> updateForm(@Valid @RequestBody BpmFormUpdateReqVO updateReqVO) {
        formService.updateForm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除动态表单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('bpm:form:delete')")
    public CommonResult<Boolean> deleteForm(@RequestParam("id") Long id) {
        formService.deleteForm(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得动态表单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:form:query')")
    public CommonResult<BpmFormRespVO> getForm(@RequestParam("id") Long id) {
        BpmFormDO form = formService.getForm(id);
        return success(BpmFormConvert.INSTANCE.convert(form));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获得动态表单的精简列表", description = "用于表单下拉框")
    public CommonResult<List<BpmFormSimpleRespVO>> getSimpleForms() {
        List<BpmFormDO> list = formService.getFormList();
        return success(BpmFormConvert.INSTANCE.convertList2(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得动态表单分页")
    @PreAuthorize("@ss.hasPermission('bpm:form:query')")
    public CommonResult<PageResult<BpmFormRespVO>> getFormPage(@Valid BpmFormPageReqVO pageVO) {
        PageResult<BpmFormDO> pageResult = formService.getFormPage(pageVO);
        return success(BpmFormConvert.INSTANCE.convertPage(pageResult));
    }

}
