package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.DeviceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Device;

import java.io.InputStream;
import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:17
 */
public interface DeviceService {


    Integer saveInspectDevice(Device inspectDevice);


    Integer removeInspectDevice(List<Long> ids);

    Device findDeviceById(Long id);

    Integer updateInspectDevice(Device inspectDevice);

    PageResult<Device> findPage(DeviceQueryVo inspectDevicePageReqVO);

    void importExcelData(InputStream inputStream);

    Device findDeviceByName(String deviceName);

    Device findDeviceByCode(String code);

    List<Device> listAll();

}
