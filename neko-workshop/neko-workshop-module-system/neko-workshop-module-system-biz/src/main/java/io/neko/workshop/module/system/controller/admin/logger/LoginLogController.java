package io.neko.workshop.module.system.controller.admin.logger;

import io.neko.workshop.module.system.dal.dataobject.logger.LoginLogDO;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.excel.core.util.ExcelUtils;
import io.neko.workshop.framework.operatelog.core.annotations.OperateLog;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogExcelVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogExportReqVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogPageReqVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogRespVO;
import io.neko.workshop.module.system.convert.logger.LoginLogConvert;
import io.neko.workshop.module.system.service.logger.LoginLogService;
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

import static io.neko.workshop.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - 登录日志")
@RestController
@RequestMapping("/system/login-log")
@Validated
@RequiredArgsConstructor
public class LoginLogController {

    private final LoginLogService loginLogService;

    @GetMapping("/page")
    @Operation(summary = "获得登录日志分页列表")
    @PreAuthorize("@ss.hasPermission('system:login-log:query')")
    public CommonResult<PageResult<LoginLogRespVO>> getLoginLogPage(@Valid LoginLogPageReqVO reqVO) {
        PageResult<LoginLogDO> page = loginLogService.getLoginLogPage(reqVO);
        return CommonResult.success(LoginLogConvert.INSTANCE.convertPage(page));
    }

    @GetMapping("/export")
    @Operation(summary = "导出登录日志 Excel")
    @PreAuthorize("@ss.hasPermission('system:login-log:export')")
    @OperateLog(type = EXPORT)
    public void exportLoginLog(HttpServletResponse response, @Valid LoginLogExportReqVO reqVO) throws IOException {
        List<LoginLogDO> list = loginLogService.getLoginLogList(reqVO);
        // 拼接数据
        List<LoginLogExcelVO> data = LoginLogConvert.INSTANCE.convertList(list);
        // 输出
        ExcelUtils.write(response, "登录日志.xls", "数据列表", LoginLogExcelVO.class, data);
    }

}
