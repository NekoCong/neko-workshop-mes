package io.neko.workshop.module.bpm.framework.flowable.core.behavior.script.impl;

import io.neko.workshop.framework.common.util.number.NumberUtils;
import io.neko.workshop.module.bpm.framework.flowable.core.behavior.script.BpmTaskAssignScript;
import io.neko.workshop.module.bpm.service.task.BpmProcessInstanceService;
import io.neko.workshop.module.system.api.dept.DeptApi;
import io.neko.workshop.module.system.api.dept.dto.DeptRespDTO;
import io.neko.workshop.module.system.api.user.AdminUserApi;
import io.neko.workshop.module.system.api.user.dto.AdminUserRespDTO;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import java.util.Set;

import static io.neko.workshop.framework.common.util.collection.SetUtils.asSet;
import static java.util.Collections.emptySet;
import lombok.RequiredArgsConstructor;

/**
 * 分配给发起人的 Leader 审批的 Script 实现类
 * 目前 Leader 的定义是，
 *
 * @author 灵喵工坊
 */
@RequiredArgsConstructor
public abstract class BpmTaskAssignLeaderAbstractScript implements BpmTaskAssignScript {

    private final AdminUserApi adminUserApi;
    private final DeptApi deptApi;
    @Lazy // 解决循环依赖
    private final BpmProcessInstanceService bpmProcessInstanceService;

    protected Set<Long> calculateTaskCandidateUsers(DelegateExecution execution, int level) {
        Assert.isTrue(level > 0, "level 必须大于 0");
        // 获得发起人
        ProcessInstance processInstance = bpmProcessInstanceService.getProcessInstance(execution.getProcessInstanceId());
        Long startUserId = NumberUtils.parseLong(processInstance.getStartUserId());
        // 获得对应 leve 的部门
        DeptRespDTO dept = null;
        for (int i = 0; i < level; i++) {
            // 获得 level 对应的部门
            if (dept == null) {
                dept = getStartUserDept(startUserId);
                if (dept == null) { // 找不到发起人的部门，所以无法使用该规则
                    return emptySet();
                }
            } else {
                DeptRespDTO parentDept = deptApi.getDept(dept.getParentId());
                if (parentDept == null) { // 找不到父级部门，所以只好结束寻找。原因是：例如说，级别比较高的人，所在部门层级比较少
                    break;
                }
                dept = parentDept;
            }
        }
        return dept.getLeaderUserId() != null ? asSet(dept.getLeaderUserId()) : emptySet();
    }

    private DeptRespDTO getStartUserDept(Long startUserId) {
        AdminUserRespDTO startUser = adminUserApi.getUser(startUserId);
        if (startUser.getDeptId() == null) { // 找不到部门，所以无法使用该规则
            return null;
        }
        return deptApi.getDept(startUser.getDeptId());
    }

}
