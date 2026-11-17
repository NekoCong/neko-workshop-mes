package io.neko.workshop.module.bpm.convert.oa;

import io.neko.workshop.module.bpm.controller.admin.oa.vo.BpmOALeaveCreateReqVO;
import io.neko.workshop.module.bpm.controller.admin.oa.vo.BpmOALeaveRespVO;
import io.neko.workshop.module.bpm.dal.dataobject.oa.BpmOALeaveDO;
import io.neko.workshop.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 请假申请 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface BpmOALeaveConvert {

    BpmOALeaveConvert INSTANCE = Mappers.getMapper(BpmOALeaveConvert.class);

    BpmOALeaveDO convert(BpmOALeaveCreateReqVO bean);

    BpmOALeaveRespVO convert(BpmOALeaveDO bean);

    List<BpmOALeaveRespVO> convertList(List<BpmOALeaveDO> list);

    PageResult<BpmOALeaveRespVO> convertPage(PageResult<BpmOALeaveDO> page);

}
