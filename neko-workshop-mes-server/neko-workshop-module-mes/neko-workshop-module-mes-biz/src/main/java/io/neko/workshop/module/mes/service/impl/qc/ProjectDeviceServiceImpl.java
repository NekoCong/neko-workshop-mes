package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProjectDevice;
import io.neko.workshop.module.mes.dal.mysql.qc.ProjectDeviceMapper;
import io.neko.workshop.module.mes.service.qc.ProjectDeviceService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/7/11 21:26
 */
@Service
@RequiredArgsConstructor
public class ProjectDeviceServiceImpl implements ProjectDeviceService {

    private final ProjectDeviceMapper projectDeviceMapper;

    @Override
    public Integer saveProjectDevice(ProjectDevice projectDevice) {
        return projectDeviceMapper.insert(projectDevice);
    }

    @Override
    public List<ProjectDevice> findProjectDeviceByCode(String projectCode) {
        LambdaQueryWrapperX<ProjectDevice> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProjectDevice::getProjectCode,projectCode);
        return projectDeviceMapper.selectList(queryWrapperX);
    }

    @Override
    public Integer removeProjectDeviceByCode(String projectCode) {
        LambdaQueryWrapperX<ProjectDevice> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProjectDevice::getProjectCode,projectCode);
        return projectDeviceMapper.delete(queryWrapperX);
    }

    @Override
    public Integer removeDeviceByDeviceCode(String code,String projectCode) {
        LambdaQueryWrapperX<ProjectDevice> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProjectDevice::getDeviceCode,code);
        queryWrapperX.eq(ProjectDevice::getProjectCode,projectCode);
        return projectDeviceMapper.delete(queryWrapperX);
    }
}
