package io.neko.workshop.module.system.convert.sms;

import io.neko.workshop.module.system.controller.admin.sms.vo.log.SmsLogExcelVO;
import io.neko.workshop.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import io.neko.workshop.module.system.dal.dataobject.sms.SmsLogDO;
import io.neko.workshop.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信日志 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface SmsLogConvert {

    SmsLogConvert INSTANCE = Mappers.getMapper(SmsLogConvert.class);

    SmsLogRespVO convert(SmsLogDO bean);

    List<SmsLogRespVO> convertList(List<SmsLogDO> list);

    PageResult<SmsLogRespVO> convertPage(PageResult<SmsLogDO> page);

    List<SmsLogExcelVO> convertList02(List<SmsLogDO> list);

}
