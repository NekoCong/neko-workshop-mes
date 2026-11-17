package io.neko.workshop.module.mes.service.impl.qc;

import com.alibaba.excel.EasyExcel;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.DeviceListener;
import io.neko.workshop.module.mes.controller.admin.qc.vo.DeviceExcelVo;
import io.neko.workshop.module.mes.controller.admin.qc.vo.DeviceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Device;
import io.neko.workshop.module.mes.dal.mysql.dept.SysDeptMapper;
import io.neko.workshop.module.mes.dal.mysql.md.WorkShopMapper;
import io.neko.workshop.module.mes.dal.mysql.pro.ProcessMapper;
import io.neko.workshop.module.mes.dal.mysql.qc.DeviceMapper;
import io.neko.workshop.module.mes.service.qc.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/6 11:17
 */
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;

    private final WorkShopMapper workshopMapper;
    private final ProcessMapper processMapper;

    private final SysDeptMapper sysDeptMapper;


    @Override
    public Integer saveInspectDevice(Device inspectDevice) {
        return deviceMapper.insert(inspectDevice);
    }

    @Override
    public Integer removeInspectDevice(List<Long> ids) {
        return deviceMapper.deleteBatchIds(ids);
    }

    @Override
    public Device findDeviceById(Long id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public Integer updateInspectDevice(Device inspectDevice) {
        return deviceMapper.updateById(inspectDevice);
    }

    @Override
    public PageResult<Device> findPage(DeviceQueryVo inspectDevicePageReqVO) {
        return deviceMapper.findPage(inspectDevicePageReqVO);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importExcelData(InputStream inputStream) {
        EasyExcel.read(inputStream, DeviceExcelVo.class,new DeviceListener(deviceMapper,workshopMapper,processMapper,sysDeptMapper)).sheet().doRead();
    }

    @Override
    public Device findDeviceByName(String deviceName) {
        LambdaQueryWrapperX<Device> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Device::getDeviceName,deviceName);
        return deviceMapper.selectOne(queryWrapperX);
    }

    @Override
    public Device findDeviceByCode(String code) {
        LambdaQueryWrapperX<Device> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Device::getDeviceCode,code);
        return deviceMapper.selectOne(queryWrapperX);
    }

    @Override
    public List<Device> listAll() {
        return deviceMapper.selectList();
    }


}
