package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ProjectClassifyQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProjectClassify;

import java.io.InputStream;
import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:40
 */
public interface ProjectClassifyService {

    Integer saveInspectProjectClassify(ProjectClassify inspectProjectClassify);


    Integer removeInspectProjectClassify(List<Long> ids);

    ProjectClassify findInspectProjectClassify(Long id);

    Integer updateInspectProjectClassify(ProjectClassify inspectProjectClassify);

    PageResult<ProjectClassify> findPage(ProjectClassifyQueryVo inspectProjectClassifyPageVO);

    void importExcelData(InputStream inputStream);

    ProjectClassify findProjectClassifyByName(String projectName);
}
