package io.neko.workshop.module.system.mq.consumer.sensitiveword;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.sensitiveword.SensitiveWordRefreshMessage;
import io.neko.workshop.module.system.service.sensitiveword.SensitiveWordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link SensitiveWordRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SensitiveWordRefreshConsumer extends AbstractChannelMessageListener<SensitiveWordRefreshMessage> {

    private final SensitiveWordService sensitiveWordService;

    @Override
    public void onMessage(SensitiveWordRefreshMessage message) {
        log.info("[onMessage][收到 SensitiveWord 刷新消息]");
        sensitiveWordService.initLocalCache();
    }

}
