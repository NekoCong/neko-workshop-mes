package io.neko.workshop.module.system.controller.admin.socail;

import io.neko.workshop.framework.common.enums.UserTypeEnum;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.module.system.controller.admin.socail.vo.SocialUserBindReqVO;
import io.neko.workshop.module.system.controller.admin.socail.vo.SocialUserUnbindReqVO;
import io.neko.workshop.module.system.convert.social.SocialUserConvert;
import io.neko.workshop.module.system.service.social.SocialUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static io.neko.workshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
@RequiredArgsConstructor
public class SocialUserController {

    private final SocialUserService socialUserService;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid SocialUserBindReqVO reqVO) {
        socialUserService.bindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody SocialUserUnbindReqVO reqVO) {
        socialUserService.unbindSocialUser(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO.getType(), reqVO.getOpenid());
        return CommonResult.success(true);
    }

}
