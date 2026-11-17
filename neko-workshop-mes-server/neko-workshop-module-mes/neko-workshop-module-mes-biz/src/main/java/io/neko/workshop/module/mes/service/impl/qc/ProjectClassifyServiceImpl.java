package io.neko.workshop.module.mes.service.impl.qc;

import com.alibaba.excel.EasyExcel;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectClassifyExcelVo;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectClassifyQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProjectClassify;
import io.neko.workshop.module.mes.dal.mysql.qc.ProjectClassifyMapper;
import io.neko.workshop.module.mes.service.qc.ProjectClassifyService;
import org.springframework.stereotype.Service;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectClassifyListener;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:40
 */
@Service
@RequiredArgsConstructor
public class ProjectClassifyServiceImpl implements ProjectClassifyService {

    private final ProjectClassifyMapper projectClassifyMapper;

    @Override
    public Integer saveInspectProjectClassify(ProjectClassify inspectProjectClassify) {
        return projectClassifyMapper.insert(inspectProjectClassify);
    }

    @Override
    public Integer removeInspectProjectClassify(List<Long> ids) {
        return projectClassifyMapper.deleteBatchIds(ids);
    }

    @Override
    public ProjectClassify findInspectProjectClassify(Long id) {
        return projectClassifyMapper.selectById(id);
    }

    @Override
    public Integer updateInspectProjectClassify(ProjectClassify inspectProjectClassify) {
        return projectClassifyMapper.updateById(inspectProjectClassify);
    }

    @Override
    public PageResult<ProjectClassify> findPage(ProjectClassifyQueryVo inspectProjectClassifyPageVO) {
        return projectClassifyMapper.findPage(inspectProjectClassifyPageVO);
    }

    @Override
    public void importExcelData(InputStream inputStream) {
        EasyExcel.read(inputStream, ProjectClassifyExcelVo.class,new ProjectClassifyListener(projectClassifyMapper)).sheet().doRead();
    }

    @Override
    public ProjectClassify findProjectClassifyByName(String projectName) {
        LambdaQueryWrapperX<ProjectClassify> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ProjectClassify::getProjectName,projectName);
        return projectClassifyMapper.selectOne(queryWrapperX);
    }


}
