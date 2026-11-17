package io.neko.workshop.module.mes.dal.mysql.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueMethodQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueMethod;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * io.neko.workshop.module.mes.dal.mysql.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 16:21
 */

@Mapper
public interface IssueMethodMapper extends BaseMapperX<IssueMethod> {
    default PageResult<IssueMethod> findPage(IssueMethodQueryVo issueMethodQueryVo) {
        LambdaQueryWrapperX<IssueMethod> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(issueMethodQueryVo.getName())){
            queryWrapperX.eq(IssueMethod::getName, issueMethodQueryVo.getName());
        }
        if(StringUtils.isNotBlank(issueMethodQueryVo.getDescription())){
            queryWrapperX.eq(IssueMethod::getDescription, issueMethodQueryVo.getDescription());
        }

        if(StringUtils.isBlank(issueMethodQueryVo.getName())
                && StringUtils.isBlank(issueMethodQueryVo.getDescription())){
            queryWrapperX.isNotNull(IssueMethod::getId);
        }
        return selectPage(issueMethodQueryVo, queryWrapperX);
    }
}
