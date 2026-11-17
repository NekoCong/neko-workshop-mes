package io.neko.workshop.module.system.convert.notice;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import io.neko.workshop.module.system.controller.admin.notice.vo.NoticeRespVO;
import io.neko.workshop.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import io.neko.workshop.module.system.dal.dataobject.notice.NoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoticeConvert {

    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);

}
