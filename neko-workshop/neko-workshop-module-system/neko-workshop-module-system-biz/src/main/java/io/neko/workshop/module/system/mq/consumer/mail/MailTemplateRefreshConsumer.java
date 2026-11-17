package io.neko.workshop.module.system.mq.consumer.mail;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.mail.MailTemplateRefreshMessage;
import io.neko.workshop.module.system.service.mail.MailTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link MailTemplateRefreshMessage} 的消费者
 *
 * @author wangjingyi
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailTemplateRefreshConsumer extends AbstractChannelMessageListener<MailTemplateRefreshMessage> {

    private final MailTemplateService mailTemplateService;

    @Override
    public void onMessage(MailTemplateRefreshMessage message) {
        log.info("[onMessage][收到 Mail Template 刷新信息]");
        mailTemplateService.initLocalCache();
    }

}
