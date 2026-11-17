package io.neko.workshop.module.system.mq.producer.permission;

import io.neko.workshop.module.system.mq.message.permission.RoleMenuRefreshMessage;
import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import io.neko.workshop.module.system.mq.message.permission.UserRoleRefreshMessage;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Permission 权限相关消息的 Producer
 */
@Component
@RequiredArgsConstructor
public class PermissionProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link RoleMenuRefreshMessage} 消息
     */
    public void sendRoleMenuRefreshMessage() {
        RoleMenuRefreshMessage message = new RoleMenuRefreshMessage();
        redisMQTemplate.send(message);
    }

    /**
     * 发送 {@link UserRoleRefreshMessage} 消息
     */
    public void sendUserRoleRefreshMessage() {
        UserRoleRefreshMessage message = new UserRoleRefreshMessage();
        redisMQTemplate.send(message);
    }

}
