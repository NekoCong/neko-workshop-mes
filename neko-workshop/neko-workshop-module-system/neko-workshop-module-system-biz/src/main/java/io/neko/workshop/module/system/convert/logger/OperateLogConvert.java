package io.neko.workshop.module.system.convert.logger;

import io.neko.workshop.module.system.controller.admin.logger.vo.operatelog.OperateLogExcelVO;
import io.neko.workshop.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import io.neko.workshop.module.system.dal.dataobject.logger.OperateLogDO;
import io.neko.workshop.module.system.dal.dataobject.user.AdminUserDO;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.common.util.collection.MapUtils;
import io.neko.workshop.module.system.api.logger.dto.OperateLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.neko.workshop.framework.common.exception.enums.GlobalErrorCodeConstants.SUCCESS;

@Mapper
public interface OperateLogConvert {

    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    OperateLogDO convert(OperateLogCreateReqDTO bean);

    PageResult<OperateLogRespVO> convertPage(PageResult<OperateLogDO> page);

    OperateLogRespVO convert(OperateLogDO bean);

    default List<OperateLogExcelVO> convertList(List<OperateLogDO> list, Map<Long, AdminUserDO> userMap) {
        return list.stream().map(operateLog -> {
            OperateLogExcelVO excelVO = convert02(operateLog);
            MapUtils.findAndThen(userMap, operateLog.getUserId(), user -> excelVO.setUserNickname(user.getNickname()));
            excelVO.setSuccessStr(SUCCESS.getCode().equals(operateLog.getResultCode()) ? "成功" : "失败");
            return excelVO;
        }).collect(Collectors.toList());
    }

    OperateLogExcelVO convert02(OperateLogDO bean);

}
