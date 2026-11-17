package io.neko.workshop.module.system.mq.consumer.auth;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.auth.OAuth2ClientRefreshMessage;
import io.neko.workshop.module.system.service.oauth2.OAuth2ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link OAuth2ClientRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2ClientRefreshConsumer extends AbstractChannelMessageListener<OAuth2ClientRefreshMessage> {

    private final OAuth2ClientService oauth2ClientService;

    @Override
    public void onMessage(OAuth2ClientRefreshMessage message) {
        log.info("[onMessage][收到 OAuth2Client 刷新消息]");
        oauth2ClientService.initLocalCache();
    }

}
