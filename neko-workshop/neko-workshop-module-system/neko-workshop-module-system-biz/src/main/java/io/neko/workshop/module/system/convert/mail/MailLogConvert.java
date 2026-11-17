package io.neko.workshop.module.system.convert.mail;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.mail.vo.log.MailLogRespVO;
import io.neko.workshop.module.system.dal.dataobject.mail.MailLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailLogConvert {

    MailLogConvert INSTANCE = Mappers.getMapper(MailLogConvert.class);

    PageResult<MailLogRespVO> convertPage(PageResult<MailLogDO> pageResult);

    MailLogRespVO convert(MailLogDO bean);

}
