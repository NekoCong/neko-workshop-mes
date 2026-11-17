package io.neko.workshop.module.mes.dal.mysql.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueType;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * io.neko.workshop.module.mes.dal.mysql.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 16:22
 */

@Mapper
public interface IssueTypeMapper extends BaseMapperX<IssueType> {

    default PageResult<IssueType> findPage(IssueTypeQueryVo issueTypeQueryVO) {
        LambdaQueryWrapperX<IssueType> queryWrapperX = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(issueTypeQueryVO.getName())){
            queryWrapperX.eq(IssueType::getName, issueTypeQueryVO.getName());
        }
        if(StringUtils.isNotBlank(issueTypeQueryVO.getSubTopic())){
            queryWrapperX.eq(IssueType::getSubTopic, issueTypeQueryVO.getSubTopic());
        }

        if(StringUtils.isBlank(issueTypeQueryVO.getName())
                && StringUtils.isBlank(issueTypeQueryVO.getSubTopic())){
            queryWrapperX.isNotNull(IssueType::getId);
        }
        return selectPage(issueTypeQueryVO, queryWrapperX);
    }

    /*@Select("SELECT type.name as type_name, mode.name as mode_name FROM issue_type as type Left Join issue_mode as mode ON type.name = mode.type")
    List<TypeAndModeVO> selectAllTypeNameWithModeNameList();*/

}
