package io.neko.workshop.module.system.mq.consumer.notify;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.notify.NotifyTemplateRefreshMessage;
import io.neko.workshop.module.system.service.notify.NotifyTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link NotifyTemplateRefreshMessage} 的消费者
 *
 * @author xrcoder
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class NotifyTemplateRefreshConsumer extends AbstractChannelMessageListener<NotifyTemplateRefreshMessage> {

    private final NotifyTemplateService notifyTemplateService;

    @Override
    public void onMessage(NotifyTemplateRefreshMessage message) {
        log.info("[onMessage][收到 NotifyTemplate 刷新消息]");
        notifyTemplateService.initLocalCache();
    }

}
