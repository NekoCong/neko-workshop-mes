package io.neko.workshop.module.mes.service.dv;

import java.util.*;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.common.TreeSelect;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.MachineryType;


/**
 * 设备类型 Service接口
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
public interface MachineryTypeService {

    /**
     * 新增设备类型
     * @param createReqVO
     * @return Integer
     */
    Integer createMachineryType( MachineryType createReqVO);

    /**
     * 修改设备类型
     * @param updateReqVO
     * @return
     */
    Integer updateMachineryType(MachineryType updateReqVO);



    /**
     * 根据id查询设备类型
     * @param id
     * @return MachineryType
     */
    MachineryType getMachineryType(Long id);


    /**
     *  设备类型条件分页查询
     * @param pageReqVO
     * @return PageResult<MachineryType>
     */
    PageResult<MachineryType> getMachineryTypePage(MachineryTypeQueryVo pageReqVO);



    /**
     * 查询设备类型
     * @param machineryTypeDO
     * @return List<MachineryType>
     */
    List<MachineryType> findItemTypeList(MachineryType machineryTypeDO);

    /**
     * 设备类型树形显示
     * @param machineryTypeList
     * @return List<TreeSelect>
     */
    List<TreeSelect> buildTreeSelect(List<MachineryType> machineryTypeList);

    /**
     * 删除设备类型通过设备类型id
     * @param machineryTypeIds
     * @return Integer
     */
    Integer deleteDvMachineryTypeByMachineryTypeId(Long machineryTypeIds);

    /**
     * 通过设备类型编号和名称进行查询
     * @param machineryTypeCode
     * @param machineryTypeName
     * @return MachineryType
     */
    MachineryType findByCodeAndName(String machineryTypeCode, String machineryTypeName);


}
