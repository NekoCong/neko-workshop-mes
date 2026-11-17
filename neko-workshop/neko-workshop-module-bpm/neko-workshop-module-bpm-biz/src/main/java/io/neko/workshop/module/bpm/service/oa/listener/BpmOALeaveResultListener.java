package io.neko.workshop.module.bpm.service.oa.listener;

import io.neko.workshop.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import io.neko.workshop.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;
import io.neko.workshop.module.bpm.service.oa.BpmOALeaveService;
import io.neko.workshop.module.bpm.service.oa.BpmOALeaveServiceImpl;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 灵喵工坊
 */
@Component
@RequiredArgsConstructor
public class BpmOALeaveResultListener extends BpmProcessInstanceResultEventListener {

    private final BpmOALeaveService leaveService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmOALeaveServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
        leaveService.updateLeaveResult(Long.parseLong(event.getBusinessKey()), event.getResult());
    }

}
