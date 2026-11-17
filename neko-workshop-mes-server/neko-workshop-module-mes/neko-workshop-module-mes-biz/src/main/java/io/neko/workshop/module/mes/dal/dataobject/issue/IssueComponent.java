package io.neko.workshop.module.mes.dal.dataobject.issue;

import com.baomidou.mybatisplus.annotation.TableName;
import io.neko.workshop.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * io.neko.workshop.module.mes.dal.dataobject.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/26 16:16
 */

@Data
@TableName("issue_component")
public class IssueComponent extends BaseDO {

    private Long id;

    /**
     * 零部件名称
     */
    @NotBlank(message = "零部件名称不能为空")
    private String name;

    /**
     * 描述
     */
    private String description;

}
