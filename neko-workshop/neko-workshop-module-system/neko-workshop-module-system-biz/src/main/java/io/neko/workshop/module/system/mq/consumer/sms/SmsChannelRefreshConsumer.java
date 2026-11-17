package io.neko.workshop.module.system.mq.consumer.sms;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.sms.SmsChannelRefreshMessage;
import io.neko.workshop.module.system.service.sms.SmsChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link SmsChannelRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SmsChannelRefreshConsumer extends AbstractChannelMessageListener<SmsChannelRefreshMessage> {

    private final SmsChannelService smsChannelService;

    @Override
    public void onMessage(SmsChannelRefreshMessage message) {
        log.info("[onMessage][收到 SmsChannel 刷新消息]");
        smsChannelService.initLocalCache();
    }

}
