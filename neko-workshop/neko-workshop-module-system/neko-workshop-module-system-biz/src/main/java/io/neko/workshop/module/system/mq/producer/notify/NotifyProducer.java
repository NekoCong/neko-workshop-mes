package io.neko.workshop.module.system.mq.producer.notify;

import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import io.neko.workshop.module.system.mq.message.notify.NotifyTemplateRefreshMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Notify 站内信相关消息的 Producer
 *
 * @author xrcoder
 * @since 2022-08-06
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyProducer {

    private final RedisMQTemplate redisMQTemplate;


    /**
     * 发送 {@link NotifyTemplateRefreshMessage} 消息
     */
    public void sendNotifyTemplateRefreshMessage() {
        NotifyTemplateRefreshMessage message = new NotifyTemplateRefreshMessage();
        redisMQTemplate.send(message);
    }


}
