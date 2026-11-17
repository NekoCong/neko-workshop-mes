package io.neko.workshop.module.infra.controller.admin.logger;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.excel.core.util.ExcelUtils;
import io.neko.workshop.framework.operatelog.core.annotations.OperateLog;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExcelVO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExportReqVO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageReqVO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import io.neko.workshop.module.infra.convert.logger.ApiAccessLogConvert;
import io.neko.workshop.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import io.neko.workshop.module.infra.service.logger.ApiAccessLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - API 访问日志")
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
@RequiredArgsConstructor
public class ApiAccessLogController {

    private final ApiAccessLogService apiAccessLogService;

    @GetMapping("/page")
    @Operation(summary = "获得API 访问日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:query')")
    public CommonResult<PageResult<ApiAccessLogRespVO>> getApiAccessLogPage(@Valid ApiAccessLogPageReqVO pageVO) {
        PageResult<ApiAccessLogDO> pageResult = apiAccessLogService.getApiAccessLogPage(pageVO);
        return success(ApiAccessLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出API 访问日志 Excel")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:export')")
    @OperateLog(type = EXPORT)
    public void exportApiAccessLogExcel(@Valid ApiAccessLogExportReqVO exportReqVO,
                                        HttpServletResponse response) throws IOException {
        List<ApiAccessLogDO> list = apiAccessLogService.getApiAccessLogList(exportReqVO);
        // 导出 Excel
        List<ApiAccessLogExcelVO> datas = ApiAccessLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "API 访问日志.xls", "数据", ApiAccessLogExcelVO.class, datas);
    }

}
