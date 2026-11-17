package io.neko.workshop.module.bpm.api.task;

import io.neko.workshop.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import io.neko.workshop.module.bpm.service.task.BpmProcessInstanceService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Flowable 流程实例 Api 实现类
 *
 * @author 灵喵工坊
 * @author jason
 */
@Service
@Validated
@RequiredArgsConstructor
public class BpmProcessInstanceApiImpl implements BpmProcessInstanceApi {

    private final BpmProcessInstanceService processInstanceService;

    @Override
    public String createProcessInstance(Long userId, @Valid BpmProcessInstanceCreateReqDTO reqDTO) {
        return processInstanceService.createProcessInstance(userId, reqDTO);
    }
}
