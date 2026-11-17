package io.neko.workshop.module.mes.dal.dataobject.issue;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * io.neko.workshop.module.mes.dal.dataobject.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 15:08
 */

@Data
@TableName("issue_mode")
public class IssueMode extends BaseDO {

    private Long id;

    /**
     * 模式名称
     */
    @NotBlank(message = "模式名称不能为空")
    private String name;

    /**
     * 类型
     */
    @NotBlank(message = "类型名称不能为空")
    private String type;

    /**
     * 描述
     */
    private String description;

}
