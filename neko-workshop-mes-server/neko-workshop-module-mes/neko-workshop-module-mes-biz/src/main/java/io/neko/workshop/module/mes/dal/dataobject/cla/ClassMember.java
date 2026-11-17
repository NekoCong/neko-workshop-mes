package io.neko.workshop.module.mes.dal.dataobject.cla;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;

/**
 * @author 灵喵工坊
 * @time 2023/6/27 15:58
 */
@Data
@TableName("cla_team_member")
public class ClassMember extends EntityCommon {

    private Long id;

    private String teamCode;

    private String teamType;

    private String teamName;

    private String personCode;

    private String userName;

    private String joinProgram;




}
