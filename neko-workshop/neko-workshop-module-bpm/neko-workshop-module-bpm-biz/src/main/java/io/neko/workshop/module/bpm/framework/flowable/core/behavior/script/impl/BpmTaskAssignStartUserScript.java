package io.neko.workshop.module.bpm.framework.flowable.core.behavior.script.impl;

import io.neko.workshop.framework.common.util.collection.SetUtils;
import io.neko.workshop.framework.common.util.number.NumberUtils;
import io.neko.workshop.module.bpm.enums.definition.BpmTaskRuleScriptEnum;
import io.neko.workshop.module.bpm.framework.flowable.core.behavior.script.BpmTaskAssignScript;
import io.neko.workshop.module.bpm.service.task.BpmProcessInstanceService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;
import lombok.RequiredArgsConstructor;

/**
 * 分配给发起人审批的 Script 实现类
 *
 * @author 灵喵工坊
 */
@Component
@RequiredArgsConstructor
public class BpmTaskAssignStartUserScript implements BpmTaskAssignScript {

    @Lazy // 解决循环依赖
    private final BpmProcessInstanceService bpmProcessInstanceService;

    @Override
    public Set<Long> calculateTaskCandidateUsers(DelegateExecution execution) {
        ProcessInstance processInstance = bpmProcessInstanceService.getProcessInstance(execution.getProcessInstanceId());
        Long startUserId = NumberUtils.parseLong(processInstance.getStartUserId());
        return SetUtils.asSet(startUserId);
    }

    @Override
    public BpmTaskRuleScriptEnum getEnum() {
        return BpmTaskRuleScriptEnum.START_USER;
    }

}
