package io.neko.workshop.module.mes.dal.mysql.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueSourceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueSource;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * io.neko.workshop.module.mes.dal.mysql.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 16:22
 */

@Mapper
public interface IssueSourceMapper extends BaseMapperX<IssueSource> {

    default PageResult<IssueSource> findPage(IssueSourceQueryVo issueSourceQueryVo) {
        LambdaQueryWrapperX<IssueSource> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(issueSourceQueryVo.getName())){
            queryWrapperX.eq(IssueSource::getName, issueSourceQueryVo.getName());
        }
        if(StringUtils.isNotBlank(issueSourceQueryVo.getDescription())){
            queryWrapperX.eq(IssueSource::getDescription, issueSourceQueryVo.getDescription());
        }

        if(StringUtils.isBlank(issueSourceQueryVo.getName())
            && StringUtils.isBlank(issueSourceQueryVo.getDescription())){
            queryWrapperX.isNotNull(IssueSource::getId);
        }
        return selectPage(issueSourceQueryVo, queryWrapperX);
    }

    @Select("select * from issue_source where name = #{name}")
    List<IssueSource> findBySourceName(String name);

}
