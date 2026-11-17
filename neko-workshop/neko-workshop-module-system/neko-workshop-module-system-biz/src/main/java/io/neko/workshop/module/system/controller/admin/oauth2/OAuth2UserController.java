package io.neko.workshop.module.system.controller.admin.oauth2;

import cn.hutool.core.collection.CollUtil;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.user.OAuth2UserInfoRespVO;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.user.OAuth2UserUpdateReqVO;
import io.neko.workshop.module.system.convert.oauth2.OAuth2UserConvert;
import io.neko.workshop.module.system.dal.dataobject.dept.DeptDO;
import io.neko.workshop.module.system.dal.dataobject.dept.PostDO;
import io.neko.workshop.module.system.dal.dataobject.user.AdminUserDO;
import io.neko.workshop.module.system.service.dept.DeptService;
import io.neko.workshop.module.system.service.dept.PostService;
import io.neko.workshop.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 提供给外部应用调用为主
 *
 * 1. 在 getUserInfo 方法上，添加 @PreAuthorize("@ss.hasScope('user.read')") 注解，声明需要满足 scope = user.read
 * 2. 在 updateUserInfo 方法上，添加 @PreAuthorize("@ss.hasScope('user.write')") 注解，声明需要满足 scope = user.write
 *
 * @author 灵喵工坊
 */
@Tag(name = "管理后台 - OAuth2.0 用户")
@RestController
@RequestMapping("/system/oauth2/user")
@Validated
@Slf4j
@RequiredArgsConstructor
public class OAuth2UserController {

    private final AdminUserService userService;
    private final DeptService deptService;
    private final PostService postService;

    @GetMapping("/get")
    @Operation(summary = "获得用户基本信息")
    @PreAuthorize("@ss.hasScope('user.read')") //
    public CommonResult<OAuth2UserInfoRespVO> getUserInfo() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        OAuth2UserInfoRespVO resp = OAuth2UserConvert.INSTANCE.convert(user);
        // 获得部门信息
        if (user.getDeptId() != null) {
            DeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(OAuth2UserConvert.INSTANCE.convert(dept));
        }
        // 获得岗位信息
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<PostDO> posts = postService.getPostList(user.getPostIds());
            resp.setPosts(OAuth2UserConvert.INSTANCE.convertList(posts));
        }
        return success(resp);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户基本信息")
    @PreAuthorize("@ss.hasScope('user.write')")
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody OAuth2UserUpdateReqVO reqVO) {
        // 这里将 UserProfileUpdateReqVO =》UserProfileUpdateReqVO 对象，实现接口的复用。
        // 主要是，AdminUserService 没有自己的 BO 对象，所以复用只能这么做
        userService.updateUserProfile(getLoginUserId(), OAuth2UserConvert.INSTANCE.convert(reqVO));
        return success(true);
    }

}
