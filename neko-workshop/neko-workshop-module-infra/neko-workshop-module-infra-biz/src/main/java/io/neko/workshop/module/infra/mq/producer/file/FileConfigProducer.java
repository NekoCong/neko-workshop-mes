package io.neko.workshop.module.infra.mq.producer.file;

import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import io.neko.workshop.module.infra.mq.message.file.FileConfigRefreshMessage;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * 文件配置相关消息的 Producer
 */
@Component
@RequiredArgsConstructor
public class FileConfigProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link FileConfigRefreshMessage} 消息
     */
    public void sendFileConfigRefreshMessage() {
        FileConfigRefreshMessage message = new FileConfigRefreshMessage();
        redisMQTemplate.send(message);
    }

}
