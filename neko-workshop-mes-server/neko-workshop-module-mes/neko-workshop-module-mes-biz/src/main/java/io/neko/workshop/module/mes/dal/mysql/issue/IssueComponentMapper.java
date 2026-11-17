package io.neko.workshop.module.mes.dal.mysql.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueComponentQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueComponent;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * io.neko.workshop.module.mes.dal.mysql.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 16:20
 */

@Mapper
public interface IssueComponentMapper extends BaseMapperX<IssueComponent> {

    default PageResult<IssueComponent> findPage(IssueComponentQueryVo issueComponentQueryVo) {
        LambdaQueryWrapperX<IssueComponent> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(issueComponentQueryVo.getName())){
            queryWrapperX.eq(IssueComponent::getName, issueComponentQueryVo.getName());
        }
        if(StringUtils.isNotBlank(issueComponentQueryVo.getDescription())){
            queryWrapperX.eq(IssueComponent::getDescription, issueComponentQueryVo.getDescription());
        }

        if(StringUtils.isBlank(issueComponentQueryVo.getName())
                && StringUtils.isBlank(issueComponentQueryVo.getDescription())){
            queryWrapperX.isNotNull(IssueComponent::getId);
        }
        return selectPage(issueComponentQueryVo, queryWrapperX);
    }

}
