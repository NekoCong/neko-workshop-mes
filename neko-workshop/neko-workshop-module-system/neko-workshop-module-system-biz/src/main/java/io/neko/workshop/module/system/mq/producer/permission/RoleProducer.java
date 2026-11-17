package io.neko.workshop.module.system.mq.producer.permission;

import io.neko.workshop.module.system.mq.message.permission.RoleRefreshMessage;
import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Role 角色相关消息的 Producer
 *
 * @author 灵喵工坊
 */
@Component
@RequiredArgsConstructor
public class RoleProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link RoleRefreshMessage} 消息
     */
    public void sendRoleRefreshMessage() {
        RoleRefreshMessage message = new RoleRefreshMessage();
        redisMQTemplate.send(message);
    }

}
