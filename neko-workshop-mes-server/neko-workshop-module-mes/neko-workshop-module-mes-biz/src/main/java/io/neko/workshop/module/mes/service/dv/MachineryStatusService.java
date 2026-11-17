package io.neko.workshop.module.mes.service.dv;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryStatusQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.MachineryStatus;
import java.util.List;


/**
 * 设备状态 Service接口
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
public interface MachineryStatusService {


    /**
     * 新增设备状态
     * @param machineryStatus
     * @return  Integer
     */
    Integer save(MachineryStatus machineryStatus);

    /**
     * 批量删除设备状态
     * @param ids
     * @return  Integer
     */
    Integer batch(List<Long> ids);

    /**
     * 根据id查询设备状态
     * @param id
     * @return  MachineryStatus
     */
    MachineryStatus getMachineryStatusById(Long id);

    /**
     * 修改设备状态
     * @param machineryStatus
     * @return  Integer
     */
    Integer update(MachineryStatus machineryStatus);

    /**
     * 设备状态条件分页查询
     * @param machineryStatusPageVO
     * @return   PageResult<MachineryStatus>
     */
    PageResult<MachineryStatus> findList(MachineryStatusQueryVo machineryStatusPageVO);

    /**
     * 根据状态名查询设备状态
     * @param statusName
     * @return  MachineryStatus
     */
    MachineryStatus findByStatusName(String statusName);
}
