package io.neko.workshop.module.mes.dal.dataobject.issue;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.module.mes.common.EntityCommon;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * io.neko.workshop.module.mes.dal.dataobject.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 15:02
 */

@Data
@TableName("issue_source")
public class IssueSource extends EntityCommon {

    private Long id;

    /**
     * 来源名称
     */
    @NotBlank(message = "来源名称不能为空")
    private String name;

    /**
     * 描述
     */
    private String description;

}
