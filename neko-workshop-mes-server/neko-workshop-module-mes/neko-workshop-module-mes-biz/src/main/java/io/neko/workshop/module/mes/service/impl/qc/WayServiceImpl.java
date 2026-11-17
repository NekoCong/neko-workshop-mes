package io.neko.workshop.module.mes.service.impl.qc;

import com.alibaba.excel.EasyExcel;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.qc.vo.WayExcelVo;
import io.neko.workshop.module.mes.controller.admin.qc.vo.WayQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.Way;
import io.neko.workshop.module.mes.dal.mysql.qc.WayMapper;
import io.neko.workshop.module.mes.service.qc.WayService;
import io.neko.workshop.module.mes.controller.admin.qc.vo.WayExcelListener;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;



/**
 * @author 灵喵工坊
 * @time 2023/7/6 10:54
 */
@Service
@RequiredArgsConstructor
public class WayServiceImpl implements WayService {

    private final WayMapper inspectWayMapper;

    @Override
    public Integer saveInspectMethod(Way inspectMethod) {
        return inspectWayMapper.insert(inspectMethod);
    }

    @Override
    public Integer removeInspectMethod(List<Long> ids) {
        return inspectWayMapper.deleteBatchIds(ids);
    }

    @Override
    public Way findMethodById(Long id) {
        return inspectWayMapper.selectById(id);
    }

    @Override
    public Integer updateInspectMethod(Way inspectMethod) {
        return inspectWayMapper.updateById(inspectMethod);
    }

    @Override
    public PageResult<Way> findPage(WayQueryVo inspectMethodPageReqVO) {
        return inspectWayMapper.findPage(inspectMethodPageReqVO);
    }

    @Override
    public void importExcelData(InputStream inputStream) {
        EasyExcel.read(inputStream, WayExcelVo.class,new WayExcelListener(inspectWayMapper)).sheet().doRead();
    }

    @Override
    public Way findMethodByName(String inspectName) {
        LambdaQueryWrapperX<Way> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Way::getInspectName,inspectName);
        return inspectWayMapper.selectOne(queryWrapperX);
    }


}
