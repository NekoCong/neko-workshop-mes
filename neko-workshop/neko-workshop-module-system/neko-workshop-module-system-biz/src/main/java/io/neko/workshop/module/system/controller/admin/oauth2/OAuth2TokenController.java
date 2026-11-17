package io.neko.workshop.module.system.controller.admin.oauth2;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenRespVO;
import io.neko.workshop.module.system.convert.auth.OAuth2TokenConvert;
import io.neko.workshop.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import io.neko.workshop.module.system.enums.logger.LoginLogTypeEnum;
import io.neko.workshop.module.system.service.auth.AdminAuthService;
import io.neko.workshop.module.system.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - OAuth2.0 令牌")
@RestController
@RequestMapping("/system/oauth2-token")
@RequiredArgsConstructor
public class OAuth2TokenController {

    private final OAuth2TokenService oauth2TokenService;
    private final AdminAuthService authService;

    @GetMapping("/page")
    @Operation(summary = "获得访问令牌分页", description = "只返回有效期内的")
    @PreAuthorize("@ss.hasPermission('system:oauth2-token:page')")
    public CommonResult<PageResult<OAuth2AccessTokenRespVO>> getAccessTokenPage(@Valid OAuth2AccessTokenPageReqVO reqVO) {
        PageResult<OAuth2AccessTokenDO> pageResult = oauth2TokenService.getAccessTokenPage(reqVO);
        return success(OAuth2TokenConvert.INSTANCE.convert(pageResult));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    @PreAuthorize("@ss.hasPermission('system:oauth2-token:delete')")
    public CommonResult<Boolean> deleteAccessToken(@RequestParam("accessToken") String accessToken) {
        authService.logout(accessToken, LoginLogTypeEnum.LOGOUT_DELETE.getType());
        return success(true);
    }

}
