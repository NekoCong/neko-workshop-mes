package io.neko.workshop.module.mes.service.pro;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.pro.vo.WorkHoursTypeVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.WorkHoursType;
import java.util.List;

/**
 * 工时类型 Service 接口
 * @author 灵喵工坊
 */
public interface WorkHoursTypeService {
    /**
     * 新增工时类型
     * @param workHoursType
     * @return Integer
     */
    Integer save(WorkHoursType workHoursType);

    /**
     * 删除工时类型
     * @param ids
     * @return Integer
     */
    Integer removeByIds(List<Long> ids);

    /**
     * 根据id查询工时类型
     * @param id
     * @return WorkHoursType
     */
    WorkHoursType findById(Long id);

    /**
     * 修改工时类型
     * @param workHoursType
     * @return Integer
     */
    Integer update(WorkHoursType workHoursType);

    /**
     * 工时类型条件分页查询
     * @param workHoursTypeVO
     * @return PageResult<WorkHoursType>
     */
    PageResult<WorkHoursType> list(WorkHoursTypeVo workHoursTypeVO);

    /**
     *  根据类型查询工时类型
     * @param hoursType
     * @return WorkHoursType
     */
    WorkHoursType selectByWorkhoursType(String hoursType);
}
