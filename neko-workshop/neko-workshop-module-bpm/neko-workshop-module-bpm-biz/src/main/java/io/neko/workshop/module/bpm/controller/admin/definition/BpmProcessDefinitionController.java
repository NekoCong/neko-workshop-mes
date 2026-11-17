package io.neko.workshop.module.bpm.controller.admin.definition;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionListReqVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionPageItemRespVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionPageReqVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionRespVO;
import io.neko.workshop.module.bpm.service.definition.BpmProcessDefinitionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - 流程定义")
@RestController
@RequestMapping("/bpm/process-definition")
@Validated
@RequiredArgsConstructor
public class BpmProcessDefinitionController {

    private final BpmProcessDefinitionService bpmDefinitionService;

    @GetMapping("/page")
    @Operation(summary = "获得流程定义分页")
    @PreAuthorize("@ss.hasPermission('bpm:process-definition:query')")
    public CommonResult<PageResult<BpmProcessDefinitionPageItemRespVO>> getProcessDefinitionPage(
            BpmProcessDefinitionPageReqVO pageReqVO) {
        return success(bpmDefinitionService.getProcessDefinitionPage(pageReqVO));
    }

    @GetMapping ("/list")
    @Operation(summary = "获得流程定义列表")
    @PreAuthorize("@ss.hasPermission('bpm:process-definition:query')")
    public CommonResult<List<BpmProcessDefinitionRespVO>> getProcessDefinitionList(
            BpmProcessDefinitionListReqVO listReqVO) {
        return success(bpmDefinitionService.getProcessDefinitionList(listReqVO));
    }

    @GetMapping ("/get-bpmn-xml")
    @Operation(summary = "获得流程定义的 BPMN XML")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:process-definition:query')")
    public CommonResult<String> getProcessDefinitionBpmnXML(@RequestParam("id") String id) {
        String bpmnXML = bpmDefinitionService.getProcessDefinitionBpmnXML(id);
        return success(bpmnXML);
    }
}
