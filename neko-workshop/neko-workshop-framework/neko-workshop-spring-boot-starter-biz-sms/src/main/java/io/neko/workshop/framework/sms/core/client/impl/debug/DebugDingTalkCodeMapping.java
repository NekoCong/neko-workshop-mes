package io.neko.workshop.framework.sms.core.client.impl.debug;

import io.neko.workshop.framework.common.exception.ErrorCode;
import io.neko.workshop.framework.common.exception.enums.GlobalErrorCodeConstants;
import io.neko.workshop.framework.sms.core.client.SmsCodeMapping;
import io.neko.workshop.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;

import java.util.Objects;

/**
 * 钉钉的 SmsCodeMapping 实现类
 *
 * @author 灵喵工坊
 */
public class DebugDingTalkCodeMapping implements SmsCodeMapping {

    @Override
    public ErrorCode apply(String apiCode) {
        return Objects.equals(apiCode, "0") ? GlobalErrorCodeConstants.SUCCESS : SmsFrameworkErrorCodeConstants.SMS_UNKNOWN;
    }

}
