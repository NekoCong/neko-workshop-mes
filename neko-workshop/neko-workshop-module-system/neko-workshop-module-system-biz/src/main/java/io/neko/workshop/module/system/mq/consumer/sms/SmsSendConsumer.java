package io.neko.workshop.module.system.mq.consumer.sms;

import io.neko.workshop.module.system.mq.message.sms.SmsSendMessage;
import io.neko.workshop.module.system.service.sms.SmsSendService;
import io.neko.workshop.framework.mq.core.stream.AbstractStreamMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link SmsSendMessage} 的消费者
 *
 * @author zzf
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SmsSendConsumer extends AbstractStreamMessageListener<SmsSendMessage> {

    private final SmsSendService smsSendService;

    @Override
    public void onMessage(SmsSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
        smsSendService.doSendSms(message);
    }

}
