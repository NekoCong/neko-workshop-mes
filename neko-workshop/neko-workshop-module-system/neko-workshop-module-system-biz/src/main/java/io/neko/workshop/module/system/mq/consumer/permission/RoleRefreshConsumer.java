package io.neko.workshop.module.system.mq.consumer.permission;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.permission.RoleRefreshMessage;
import io.neko.workshop.module.system.service.permission.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link RoleRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleRefreshConsumer extends AbstractChannelMessageListener<RoleRefreshMessage> {

    private final RoleService roleService;

    @Override
    public void onMessage(RoleRefreshMessage message) {
        log.info("[onMessage][收到 Role 刷新消息]");
        roleService.initLocalCache();
    }

}
