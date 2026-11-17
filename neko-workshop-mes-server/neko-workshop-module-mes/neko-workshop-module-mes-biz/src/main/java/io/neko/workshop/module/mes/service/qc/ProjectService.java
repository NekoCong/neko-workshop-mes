package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Project;
import java.io.InputStream;
import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 13:21
 */
public interface ProjectService {

    Integer saveInspectProject(Project inspectProject);

    Integer removeInspectProject(List<Long> ids);

    Project findInspectProjectById(Long id);

    Integer updateInspectProject(Project inspectProject);

    PageResult<Project> findPage(ProjectQueryVo inspectProjectPageReqVO);

    void imporExceltData(InputStream inputStream);

    Project findInspectProjectByName(String projectName);
}
