package io.neko.workshop.module.mes.dal.mysql.plan;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.plan.vo.query.SelectPlanMonthQuery;
import io.neko.workshop.module.mes.dal.dataobject.plan.PlanMonth;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * io.neko.workshop.module.mes.dal.mysql.plan
 *
 * @author 灵喵工坊
 * @time 2023/8/3 17:23
 */

@Mapper
public interface PlanMonthMapper extends BaseMapperX<PlanMonth> {
    default PageResult<PlanMonth> pageQuery(SelectPlanMonthQuery selectPlanMonthQuery) {

        LambdaQueryWrapperX<PlanMonth> queryWrapperX = new LambdaQueryWrapperX<>();

        if (StringUtils.isNotBlank(selectPlanMonthQuery.getMonth()))
            queryWrapperX.eq(PlanMonth::getMonth, selectPlanMonthQuery.getMonth());

        if (StringUtils.isBlank(selectPlanMonthQuery.getMonth()))
            queryWrapperX.isNotNull(PlanMonth::getId);

        return selectPage(selectPlanMonthQuery, queryWrapperX);
    }

    //确认是否存在过相同的number
    @Select("select * from plan_month where number = #{number} and deleted = 1")
    PlanMonth selectOneFromDeleted(String number);
}
