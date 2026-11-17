package io.neko.workshop.module.system.mq.consumer.permission;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.permission.UserRoleRefreshMessage;
import io.neko.workshop.module.system.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link UserRoleRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserRoleRefreshConsumer extends AbstractChannelMessageListener<UserRoleRefreshMessage> {

    private final PermissionService permissionService;

    @Override
    public void onMessage(UserRoleRefreshMessage message) {
        log.info("[onMessage][收到 User 与 Role 的关联刷新消息]");
        permissionService.initLocalCache();
    }

}
