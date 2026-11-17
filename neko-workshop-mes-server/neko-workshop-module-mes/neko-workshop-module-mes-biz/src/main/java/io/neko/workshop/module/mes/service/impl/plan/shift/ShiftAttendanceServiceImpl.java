package io.neko.workshop.module.mes.service.impl.plan.shift;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.query.SelectShiftAttendanceQuery;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.request.InsertShiftAttendanceReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.request.UpdateShiftAttendanceReqVo;
import io.neko.workshop.module.mes.controller.admin.plan.shift.vo.response.SelectShiftAttendanceResVo;
import io.neko.workshop.module.mes.dal.dataobject.plan.shift.ShiftAttendance;
import io.neko.workshop.module.mes.dal.mysql.plan.shift.ShiftAttendanceMapper;
import io.neko.workshop.module.mes.service.plan.shift.ShiftAttendanceService;
import io.neko.workshop.module.mes.utils.BeanCopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.dal.service.impl.plan.shift
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:25
 */

@Service
@RequiredArgsConstructor
public class ShiftAttendanceServiceImpl implements ShiftAttendanceService {

    private final ShiftAttendanceMapper shiftAttendanceMapper;

    DecimalFormat df = new DecimalFormat("0.0000");

    @Override
    public Integer saveShiftAttendance(InsertShiftAttendanceReqVo insertShiftAttendanceReqVo) {
        //vo -> bean
        ShiftAttendance shiftAttendance = new ShiftAttendance();
        BeanUtils.copyProperties(insertShiftAttendanceReqVo, shiftAttendance);
        //检查考勤编号是否已经存在或被废弃
        if (shiftAttendanceMapper.selectOne(Wrappers.lambdaQuery(ShiftAttendance.class).eq(ShiftAttendance::getNumber, shiftAttendance.getNumber())) != null
                || shiftAttendanceMapper.selectOneFromDeleted(shiftAttendance.getNumber()) != null)
            return -1;
        shiftAttendance.setAttendanceRate(null);
        //如果填写了应到和实到 则计算出勤率
        if (shiftAttendance.getAttendanceActual() != null && shiftAttendance.getAttendanceRequired() != null)
            shiftAttendance.setAttendanceRate(Double.valueOf(df.format((double) shiftAttendance.getAttendanceActual() / shiftAttendance.getAttendanceRequired())));
        //必须同时提交应到实到人数
        else if (shiftAttendance.getAttendanceActual() != null || shiftAttendance.getAttendanceRequired() != null)
            return -2;
        return shiftAttendanceMapper.insert(shiftAttendance);
    }

    @Override
    public Integer removeShiftAttendanceByIds(List<Long> ids) {
        return shiftAttendanceMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateShiftAttendanceById(UpdateShiftAttendanceReqVo updateShiftAttendanceReqVo) {
        //修改的考勤id是否存在
        if (shiftAttendanceMapper.selectById(updateShiftAttendanceReqVo.getId()) == null)
            return -1;

        ShiftAttendance shiftAttendance = new ShiftAttendance();
        BeanUtils.copyProperties(updateShiftAttendanceReqVo, shiftAttendance);

        //若修改了应到实到人数，则重新计算考勤率
        if (shiftAttendance.getAttendanceActual() != null && shiftAttendance.getAttendanceRequired() != null)
            shiftAttendance.setAttendanceRate(Double.valueOf(df.format((double) shiftAttendance.getAttendanceActual() / shiftAttendance.getAttendanceRequired())));
        //必须同时提交应到实到人数
        else if (shiftAttendance.getAttendanceActual() != null || shiftAttendance.getAttendanceRequired() != null)
            return -2;

        return shiftAttendanceMapper.updateById(shiftAttendance);
    }

    @Override
    public SelectShiftAttendanceResVo selectShiftAttendanceById(Long id) {
        //bean -> vo
        SelectShiftAttendanceResVo selectShiftAttendanceResVo = new SelectShiftAttendanceResVo();
        //避免id不存在时的空指针
        try {
            BeanUtils.copyProperties(shiftAttendanceMapper.selectById(id), selectShiftAttendanceResVo);
        } catch (Exception e) {
            System.out.println("error: " + e);
            //报空指针时直接返回null
            return null;
        }
        return selectShiftAttendanceResVo;
    }

    @Override
    public PageResult<SelectShiftAttendanceResVo> selectShiftAttendanceList(SelectShiftAttendanceQuery selectShiftAttendanceQuery) {
        //bean list -> vo list
        List<SelectShiftAttendanceResVo> selectShiftAttendanceResVoList = BeanCopyUtil.copyListProperties(shiftAttendanceMapper.pageQuery(selectShiftAttendanceQuery).getList(), SelectShiftAttendanceResVo::new);
        return new PageResult<>(selectShiftAttendanceResVoList, (long) selectShiftAttendanceResVoList.size());
    }

}
