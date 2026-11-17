package io.neko.workshop.module.member.controller.app.social;

import io.neko.workshop.framework.common.enums.UserTypeEnum;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import io.neko.workshop.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import io.neko.workshop.module.member.convert.social.SocialUserConvert;
import io.neko.workshop.module.system.api.social.SocialUserApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static io.neko.workshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import lombok.RequiredArgsConstructor;

@Tag(name = "用户 App - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
@RequiredArgsConstructor
public class AppSocialUserController {

    private final SocialUserApi socialUserApi;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid AppSocialUserBindReqVO reqVO) {
        socialUserApi.bindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody AppSocialUserUnbindReqVO reqVO) {
        socialUserApi.unbindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

}
