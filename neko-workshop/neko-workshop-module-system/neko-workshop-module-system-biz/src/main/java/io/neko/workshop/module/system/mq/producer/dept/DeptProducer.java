package io.neko.workshop.module.system.mq.producer.dept;

import io.neko.workshop.module.system.mq.message.dept.DeptRefreshMessage;
import io.neko.workshop.framework.mq.core.RedisMQTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Dept 部门相关消息的 Producer
 */
@Component
@RequiredArgsConstructor
public class DeptProducer {

    private final RedisMQTemplate redisMQTemplate;

    /**
     * 发送 {@link DeptRefreshMessage} 消息
     */
    public void sendDeptRefreshMessage() {
        DeptRefreshMessage message = new DeptRefreshMessage();
        redisMQTemplate.send(message);
    }

}
