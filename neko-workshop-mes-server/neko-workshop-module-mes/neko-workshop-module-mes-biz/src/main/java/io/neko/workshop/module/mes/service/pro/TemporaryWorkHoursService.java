package io.neko.workshop.module.mes.service.pro;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.pro.vo.TemporaryWorkHoursExcelVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.TemporaryWorkHoursVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.TemporaryWorkHours;
import java.util.List;

/**
 * 临时工时 Service 接口
 * @author 灵喵工坊
 */
public interface TemporaryWorkHoursService {


    /**
     * 临时工时条件分页查询
     * @param temporaryWorkHoursVO
     * @return PageResult<TemporaryWorkHours>
     */
    PageResult<TemporaryWorkHours> list(TemporaryWorkHoursVo temporaryWorkHoursVO);

    /**
     * 新增临时工时
     * @param temporaryWorkHours
     * @return Integer
     */
    Integer save(TemporaryWorkHours temporaryWorkHours);


    /**
     * 删除临时工时
     * @param ids
     * @return Integer
     */
    Integer removeByIds(List<Long> ids);


    /**
     * 根据id查询临时工时
     * @param id
     * @return TemporaryWorkHours
     */
    TemporaryWorkHours findTemporaryWorkHoursById(Long id);


    /**
     * 修改临时工时
     * @param temporaryWorkHours
     * @return Integer
     */
    Integer update(TemporaryWorkHours temporaryWorkHours);

    /**
     * 导出临时工时
     * @param ids
     * @return List<TemporaryWorkHoursExcelVO>
     */
    List<TemporaryWorkHoursExcelVo> listData(List<Long> ids);
}
