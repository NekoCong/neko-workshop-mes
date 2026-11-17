package io.neko.workshop.module.mes.dal.dataobject.pro;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;


/**
 * 报工状态
 * @author 灵喵工坊
 */
@Data
@TableName("pro_feedback_status")
public class FeedbackStatus {


    private Long id;

    private String taskCode;

    private String status;

    private LocalDateTime time;

    private  Long  feedbackId;





}
