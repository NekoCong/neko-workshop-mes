package io.neko.workshop.module.mes.dal.dataobject.pro;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 报工设备状态
 * @author 灵喵工坊
 */
@Data
@TableName("pro_feedback_equ_status")
public class FeedbackEquStatus {


    private Long id;

    private String status;

    private LocalDateTime time;

    private String  remork;

    private String taskCode;




}
