package io.neko.workshop.module.mes.dal.mysql.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueModeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueMode;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * io.neko.workshop.module.mes.dal.mysql.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 16:22
 */

@Mapper
public interface IssueModeMapper extends BaseMapperX<IssueMode> {default PageResult<IssueMode> findPage(IssueModeQueryVo issueModeQueryVo) {
    LambdaQueryWrapperX<IssueMode> queryWrapperX = new LambdaQueryWrapperX<>();
    if(StringUtils.isNotBlank(issueModeQueryVo.getName())){
        queryWrapperX.eq(IssueMode::getName, issueModeQueryVo.getName());
    }
    if(StringUtils.isNotBlank(issueModeQueryVo.getType())){
        queryWrapperX.eq(IssueMode::getType, issueModeQueryVo.getType());
    }
    if(StringUtils.isNotBlank(issueModeQueryVo.getDescription())){
        queryWrapperX.eq(IssueMode::getDescription, issueModeQueryVo.getDescription());
    }

    if(StringUtils.isBlank(issueModeQueryVo.getName())
            && StringUtils.isBlank(issueModeQueryVo.getType())
            && StringUtils.isBlank(issueModeQueryVo.getDescription())){
        queryWrapperX.isNotNull(IssueMode::getId);
    }
    return selectPage(issueModeQueryVo, queryWrapperX);
}
    
}
