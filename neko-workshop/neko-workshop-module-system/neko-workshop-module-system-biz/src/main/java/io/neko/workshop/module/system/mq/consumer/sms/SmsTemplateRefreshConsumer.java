package io.neko.workshop.module.system.mq.consumer.sms;

import io.neko.workshop.module.system.mq.message.sms.SmsTemplateRefreshMessage;
import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.service.sms.SmsTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link SmsTemplateRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SmsTemplateRefreshConsumer extends AbstractChannelMessageListener<SmsTemplateRefreshMessage> {

    private final SmsTemplateService smsTemplateService;

    @Override
    public void onMessage(SmsTemplateRefreshMessage message) {
        log.info("[onMessage][收到 SmsTemplate 刷新消息]");
        smsTemplateService.initLocalCache();
    }

}
