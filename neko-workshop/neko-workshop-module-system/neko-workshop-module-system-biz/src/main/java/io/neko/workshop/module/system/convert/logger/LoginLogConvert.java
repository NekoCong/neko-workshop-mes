package io.neko.workshop.module.system.convert.logger;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.api.logger.dto.LoginLogCreateReqDTO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogExcelVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.loginlog.LoginLogRespVO;
import io.neko.workshop.module.system.dal.dataobject.logger.LoginLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoginLogConvert {

    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);

    List<LoginLogExcelVO> convertList(List<LoginLogDO> list);

    LoginLogDO convert(LoginLogCreateReqDTO bean);

}
