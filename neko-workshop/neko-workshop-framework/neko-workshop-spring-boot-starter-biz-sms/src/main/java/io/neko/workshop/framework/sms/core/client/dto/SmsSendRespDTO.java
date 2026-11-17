package io.neko.workshop.framework.sms.core.client.dto;

import lombok.Data;

/**
 * 短信发送 Response DTO
 *
 * @author 灵喵工坊
 */
@Data
public class SmsSendRespDTO {

    /**
     * 短信 API 发送返回的序号
     */
    private String serialNo;

}
