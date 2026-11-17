package io.neko.workshop.module.mes.dal.dataobject.cla;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;


/**
 * 班组实体类
 * @author 灵喵工坊
 * @time 2023/6/26 9:39
 */
@Data
@TableName("cla_team")
public class ClassTeam extends EntityCommon {

    private Long id;

    private String teamCode;

    private String teamType;

    private String teamName;

    private String teamLeader;

    private String remark;




}
