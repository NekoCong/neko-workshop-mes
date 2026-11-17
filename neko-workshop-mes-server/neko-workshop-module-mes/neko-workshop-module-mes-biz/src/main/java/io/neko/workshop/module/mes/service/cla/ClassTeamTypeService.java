package io.neko.workshop.module.mes.service.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaTeamTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassTeamType;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaTeamTypeExcelVo;
import java.util.List;

/**
 * 班组类型 Service接口
 * @author 灵喵工坊
 */
public interface ClassTeamTypeService {

    /**
     * 新增班组类型
     * @param calTeamType
     * @return Integer
     */
    Integer saveCalTeamType(ClassTeamType calTeamType);

    /**
     * 删除班组类型
     * @param ids
     * @return Integer
     */
    Integer removeCalTeamTypeByIds(List<Long> ids);

    /**
     * 修改班组类型
     * @param calTeamType
     * @return Integer
     */
    Integer updateCalTeamType(ClassTeamType calTeamType);

    /**
     * 班组类型条件分页查询
     * @param calTeamTypePageReqVO
     * @return PageResult<CalTeamType>
     */
    PageResult<ClassTeamType> calTeamTypePage(ClaTeamTypeQueryVo calTeamTypePageReqVO);

    /**
     * 班组类型详情
     * @param id
     * @return CalTeamType
     */
    ClassTeamType findCalTeamTypeById(Long id);

    /**
     * 班组类型导出
     * @return List<CalTeamTypeExcelVO>
     */
    List<ClaTeamTypeExcelVo> exportData();


    /**
     * 根据班组类型名称进行查询
     * @param typeName
     * @return CalTeamType
     */
    ClassTeamType findCalTeamTypeByName(String typeName);
}
