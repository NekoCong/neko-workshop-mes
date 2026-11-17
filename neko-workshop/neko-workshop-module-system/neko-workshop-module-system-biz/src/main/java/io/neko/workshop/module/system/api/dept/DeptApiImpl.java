package io.neko.workshop.module.system.api.dept;

import io.neko.workshop.module.system.api.dept.dto.DeptRespDTO;
import io.neko.workshop.module.system.convert.dept.DeptConvert;
import io.neko.workshop.module.system.dal.dataobject.dept.DeptDO;
import io.neko.workshop.module.system.service.dept.DeptService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 部门 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class DeptApiImpl implements DeptApi {

    private final DeptService deptService;

    @Override
    public DeptRespDTO getDept(Long id) {
        DeptDO dept = deptService.getDept(id);
        return DeptConvert.INSTANCE.convert03(dept);
    }

    @Override
    public List<DeptRespDTO> getDeptList(Collection<Long> ids) {
        List<DeptDO> depts = deptService.getDeptList(ids);
        return DeptConvert.INSTANCE.convertList03(depts);
    }

    @Override
    public void validateDeptList(Collection<Long> ids) {
        deptService.validateDeptList(ids);
    }

}
