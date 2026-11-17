package io.neko.workshop.module.system.mq.consumer.mail;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.mail.MailAccountRefreshMessage;
import io.neko.workshop.module.system.service.mail.MailAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link MailAccountRefreshMessage} 的消费者
 *
 * @author wangjingyi
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailAccountRefreshConsumer extends AbstractChannelMessageListener<MailAccountRefreshMessage> {

    private final MailAccountService mailAccountService;

    @Override
    public void onMessage(MailAccountRefreshMessage message) {
        log.info("[onMessage][收到 Mail Account 刷新信息]");
        mailAccountService.initLocalCache();
    }

}
