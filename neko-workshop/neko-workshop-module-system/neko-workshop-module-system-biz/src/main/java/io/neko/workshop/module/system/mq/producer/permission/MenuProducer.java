package io.neko.workshop.module.system.mq.producer.permission;

import io.neko.workshop.module.system.mq.message.permission.MenuRefreshMessage;
import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Menu 菜单相关消息的 Producer
 */
@Component
@RequiredArgsConstructor
public class MenuProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link MenuRefreshMessage} 消息
     */
    public void sendMenuRefreshMessage() {
        MenuRefreshMessage message = new MenuRefreshMessage();
        redisMQTemplate.send(message);
    }

}
