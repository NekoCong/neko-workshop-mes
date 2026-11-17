package io.neko.workshop.module.bpm.controller.admin.definition;

import io.neko.workshop.module.bpm.controller.admin.definition.vo.group.BpmUserGroupCreateReqVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.group.BpmUserGroupPageReqVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.group.BpmUserGroupRespVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.group.BpmUserGroupUpdateReqVO;
import io.neko.workshop.module.bpm.convert.definition.BpmUserGroupConvert;
import io.neko.workshop.module.bpm.dal.dataobject.definition.BpmUserGroupDO;
import io.neko.workshop.module.bpm.service.definition.BpmUserGroupService;
import io.neko.workshop.framework.common.enums.CommonStatusEnum;
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

@Tag(name = "管理后台 - 用户组")
@RestController
@RequestMapping("/bpm/user-group")
@Validated
@RequiredArgsConstructor
public class BpmUserGroupController {

    private final BpmUserGroupService userGroupService;

    @PostMapping("/create")
    @Operation(summary = "创建用户组")
    @PreAuthorize("@ss.hasPermission('bpm:user-group:create')")
    public CommonResult<Long> createUserGroup(@Valid @RequestBody BpmUserGroupCreateReqVO createReqVO) {
        return success(userGroupService.createUserGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户组")
    @PreAuthorize("@ss.hasPermission('bpm:user-group:update')")
    public CommonResult<Boolean> updateUserGroup(@Valid @RequestBody BpmUserGroupUpdateReqVO updateReqVO) {
        userGroupService.updateUserGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('bpm:user-group:delete')")
    public CommonResult<Boolean> deleteUserGroup(@RequestParam("id") Long id) {
        userGroupService.deleteUserGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:user-group:query')")
    public CommonResult<BpmUserGroupRespVO> getUserGroup(@RequestParam("id") Long id) {
        BpmUserGroupDO userGroup = userGroupService.getUserGroup(id);
        return success(BpmUserGroupConvert.INSTANCE.convert(userGroup));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户组分页")
    @PreAuthorize("@ss.hasPermission('bpm:user-group:query')")
    public CommonResult<PageResult<BpmUserGroupRespVO>> getUserGroupPage(@Valid BpmUserGroupPageReqVO pageVO) {
        PageResult<BpmUserGroupDO> pageResult = userGroupService.getUserGroupPage(pageVO);
        return success(BpmUserGroupConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获取用户组精简信息列表", description = "只包含被开启的用户组，主要用于前端的下拉选项")
    public CommonResult<List<BpmUserGroupRespVO>> getSimpleUserGroups() {
        // 获用户门列表，只要开启状态的
        List<BpmUserGroupDO> list = userGroupService.getUserGroupListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 排序后，返回给前端
        return success(BpmUserGroupConvert.INSTANCE.convertList2(list));
    }

}
