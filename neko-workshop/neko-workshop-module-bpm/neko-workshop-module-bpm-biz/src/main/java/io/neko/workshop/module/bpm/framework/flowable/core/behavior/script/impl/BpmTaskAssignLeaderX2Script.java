package io.neko.workshop.module.bpm.framework.flowable.core.behavior.script.impl;

import io.neko.workshop.module.bpm.enums.definition.BpmTaskRuleScriptEnum;
import io.neko.workshop.module.bpm.service.task.BpmProcessInstanceService;
import io.neko.workshop.module.system.api.dept.DeptApi;
import io.neko.workshop.module.system.api.user.AdminUserApi;
import org.springframework.context.annotation.Lazy;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 分配给发起人的二级 Leader 审批的 Script 实现类
 *
 * @author 灵喵工坊
 */
@Component
public class BpmTaskAssignLeaderX2Script extends BpmTaskAssignLeaderAbstractScript {

    public BpmTaskAssignLeaderX2Script(AdminUserApi adminUserApi, DeptApi deptApi, @Lazy BpmProcessInstanceService bpmProcessInstanceService) {
        super(adminUserApi, deptApi, bpmProcessInstanceService);
    }

    @Override
    public Set<Long> calculateTaskCandidateUsers(DelegateExecution execution) {
        return calculateTaskCandidateUsers(execution, 2);
    }

    @Override
    public BpmTaskRuleScriptEnum getEnum() {
        return BpmTaskRuleScriptEnum.LEADER_X2;
    }

}
