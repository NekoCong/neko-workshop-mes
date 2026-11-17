package io.neko.workshop.module.mes.service.impl.dept;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.dept.SysDept;
import io.neko.workshop.module.mes.dal.mysql.dept.SysDeptMapper;
import io.neko.workshop.module.mes.service.dept.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/7 15:02
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl implements SysDeptService {


    private final SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> findDeptList() {
        return sysDeptMapper.selectList();
    }

    @Override
    public SysDept findDeptById(String department) {
        LambdaQueryWrapperX<SysDept> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SysDept::getId,department);
        return sysDeptMapper.selectOne(queryWrapperX);
    }

    @Override
    public SysDept findDeptByName(String department ) {
        LambdaQueryWrapperX<SysDept> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SysDept::getName,department);
        queryWrapperX.last("LIMIT 1");
        return sysDeptMapper.selectOne(queryWrapperX);
    }


}
