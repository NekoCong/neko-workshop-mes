package io.neko.workshop.module.bpm.convert.definition;

import io.neko.workshop.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.form.BpmFormRespVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.form.BpmFormSimpleRespVO;
import io.neko.workshop.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import io.neko.workshop.module.bpm.dal.dataobject.definition.BpmFormDO;
import io.neko.workshop.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态表单 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface BpmFormConvert {

    BpmFormConvert INSTANCE = Mappers.getMapper(BpmFormConvert.class);

    BpmFormDO convert(BpmFormCreateReqVO bean);

    BpmFormDO convert(BpmFormUpdateReqVO bean);

    BpmFormRespVO convert(BpmFormDO bean);

    List<BpmFormSimpleRespVO> convertList2(List<BpmFormDO> list);

    PageResult<BpmFormRespVO> convertPage(PageResult<BpmFormDO> page);

}
