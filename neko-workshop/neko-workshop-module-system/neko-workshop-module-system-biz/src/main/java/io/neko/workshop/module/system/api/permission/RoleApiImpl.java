package io.neko.workshop.module.system.api.permission;

import io.neko.workshop.module.system.service.permission.RoleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

/**
 * 角色 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class RoleApiImpl implements RoleApi {

    private final RoleService roleService;

    @Override
    public void validRoleList(Collection<Long> ids) {
        roleService.validateRoleList(ids);
    }
}
