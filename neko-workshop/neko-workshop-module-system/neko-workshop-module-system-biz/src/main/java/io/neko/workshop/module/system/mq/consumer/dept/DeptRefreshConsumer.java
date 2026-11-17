package io.neko.workshop.module.system.mq.consumer.dept;

import io.neko.workshop.framework.mq.core.pubsub.AbstractChannelMessageListener;
import io.neko.workshop.module.system.mq.message.dept.DeptRefreshMessage;
import io.neko.workshop.module.system.service.dept.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 针对 {@link DeptRefreshMessage} 的消费者
 *
 * @author 灵喵工坊
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DeptRefreshConsumer extends AbstractChannelMessageListener<DeptRefreshMessage> {

    private final DeptService deptService;

    @Override
    public void onMessage(DeptRefreshMessage message) {
        log.info("[onMessage][收到 Dept 刷新消息]");
        deptService.initLocalCache();
    }

}
