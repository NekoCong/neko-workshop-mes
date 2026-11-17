package io.neko.workshop.module.infra.convert.logger;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExcelVO;
import io.neko.workshop.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogRespVO;
import io.neko.workshop.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 访问日志 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface ApiAccessLogConvert {

    ApiAccessLogConvert INSTANCE = Mappers.getMapper(ApiAccessLogConvert.class);

    ApiAccessLogRespVO convert(ApiAccessLogDO bean);

    List<ApiAccessLogRespVO> convertList(List<ApiAccessLogDO> list);

    PageResult<ApiAccessLogRespVO> convertPage(PageResult<ApiAccessLogDO> page);

    List<ApiAccessLogExcelVO> convertList02(List<ApiAccessLogDO> list);

    ApiAccessLogDO convert(ApiAccessLogCreateReqDTO bean);

}
