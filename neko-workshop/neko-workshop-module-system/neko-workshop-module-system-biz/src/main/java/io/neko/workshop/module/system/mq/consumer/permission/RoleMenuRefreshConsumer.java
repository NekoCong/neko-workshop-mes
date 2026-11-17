package io.neko.workshop.module.system.mq.consumer.permission;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.permission.RoleMenuRefreshMessage;
import io.neko.workshop.module.system.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link RoleMenuRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleMenuRefreshConsumer extends AbstractChannelMessageListener<RoleMenuRefreshMessage> {

    private final PermissionService permissionService;

    @Override
    public void onMessage(RoleMenuRefreshMessage message) {
        log.info("[onMessage][收到 Role 与 Menu 的关联刷新消息]");
        permissionService.initLocalCache();
    }

}
