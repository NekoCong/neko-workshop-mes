package io.neko.workshop.module.system.api.permission;

import io.neko.workshop.module.system.api.permission.dto.DeptDataPermissionRespDTO;
import io.neko.workshop.module.system.service.permission.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import lombok.RequiredArgsConstructor;

/**
 * 权限 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class PermissionApiImpl implements PermissionApi {

    private final PermissionService permissionService;

    @Override
    public Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return permissionService.getUserRoleIdListByRoleIds(roleIds);
    }

    @Override
    public boolean hasAnyPermissions(Long userId, String... permissions) {
        return permissionService.hasAnyPermissions(userId, permissions);
    }

    @Override
    public boolean hasAnyRoles(Long userId, String... roles) {
        return permissionService.hasAnyRoles(userId, roles);
    }

    @Override
    public DeptDataPermissionRespDTO getDeptDataPermission(Long userId) {
        return permissionService.getDeptDataPermission(userId);
    }

}
