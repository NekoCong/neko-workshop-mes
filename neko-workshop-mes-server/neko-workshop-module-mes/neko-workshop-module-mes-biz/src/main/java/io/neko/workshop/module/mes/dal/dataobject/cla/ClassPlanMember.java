package io.neko.workshop.module.mes.dal.dataobject.cla;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;
import java.math.BigDecimal;


/**
 * @author 灵喵工坊
 * @time 2023/6/30 13:39
 */
@Data
@TableName("cla_plan_team_people")
public class ClassPlanMember extends EntityCommon {

    private Long id;

    private String planCode;

    private String teamType;

    private String teamCode;

    private String teamName;

    private String planName;

    private Long peopleId;

    private String planPeopleName;

    private BigDecimal peopleQuantity;


    private String taskCode;



}
