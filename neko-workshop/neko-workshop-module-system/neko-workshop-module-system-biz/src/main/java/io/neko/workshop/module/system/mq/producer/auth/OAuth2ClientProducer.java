package io.neko.workshop.module.system.mq.producer.auth;

import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import io.neko.workshop.module.system.mq.message.auth.OAuth2ClientRefreshMessage;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * OAuth 2.0 客户端相关消息的 Producer
 */
@Component
@RequiredArgsConstructor
public class OAuth2ClientProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link OAuth2ClientRefreshMessage} 消息
     */
    public void sendOAuth2ClientRefreshMessage() {
        OAuth2ClientRefreshMessage message = new OAuth2ClientRefreshMessage();
        redisMQTemplate.send(message);
    }

}
