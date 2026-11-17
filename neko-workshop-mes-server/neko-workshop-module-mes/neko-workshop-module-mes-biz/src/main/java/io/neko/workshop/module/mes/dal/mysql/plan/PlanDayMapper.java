package io.neko.workshop.module.mes.dal.mysql.plan;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.plan.vo.query.SelectPlanDayQuery;
import io.neko.workshop.module.mes.dal.dataobject.plan.PlanDay;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * io.neko.workshop.module.mes.dal.mysql.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:24
 */

@Mapper
public interface PlanDayMapper extends BaseMapperX<PlanDay> {

    default PageResult<PlanDay> pageQuery(SelectPlanDayQuery selectPlanDayQuery) {
        LambdaQueryWrapperX<PlanDay> queryWrapperX = new LambdaQueryWrapperX<>();

        if (StringUtils.isNotBlank(selectPlanDayQuery.getDay()))
            queryWrapperX.eq(PlanDay::getDay, selectPlanDayQuery.getDay());

        if (StringUtils.isBlank(selectPlanDayQuery.getDay()))
            queryWrapperX.isNotNull(PlanDay::getId);

        return selectPage(selectPlanDayQuery, queryWrapperX);
    }

    //确认是否存在过相同的number
    @Select("select * from plan_day where number = #{number} and deleted = 1")
    PlanDay selectOneFromDeleted(String number);
}
