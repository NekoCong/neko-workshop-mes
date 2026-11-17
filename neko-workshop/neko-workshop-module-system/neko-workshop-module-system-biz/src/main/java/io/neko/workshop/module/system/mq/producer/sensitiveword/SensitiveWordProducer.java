package io.neko.workshop.module.system.mq.producer.sensitiveword;

import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import io.neko.workshop.module.system.mq.message.sensitiveword.SensitiveWordRefreshMessage;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * 敏感词相关的 Producer
 */
@Component
@RequiredArgsConstructor
public class SensitiveWordProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link SensitiveWordRefreshMessage} 消息
     */
    public void sendSensitiveWordRefreshMessage() {
        SensitiveWordRefreshMessage message = new SensitiveWordRefreshMessage();
        redisMQTemplate.send(message);
    }

}
