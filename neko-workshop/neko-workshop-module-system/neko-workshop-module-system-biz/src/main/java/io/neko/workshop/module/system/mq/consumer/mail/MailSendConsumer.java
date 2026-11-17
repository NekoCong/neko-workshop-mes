package io.neko.workshop.module.system.mq.consumer.mail;

import io.neko.workshop.framework.mq.core.stream.AbstractStreamMessageListener;
import io.neko.workshop.module.system.mq.message.mail.MailSendMessage;
import io.neko.workshop.module.system.service.mail.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link MailSendMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailSendConsumer extends AbstractStreamMessageListener<MailSendMessage> {

    private final MailSendService mailSendService;

    @Override
    public void onMessage(MailSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
        mailSendService.doSendMail(message);
    }

}
