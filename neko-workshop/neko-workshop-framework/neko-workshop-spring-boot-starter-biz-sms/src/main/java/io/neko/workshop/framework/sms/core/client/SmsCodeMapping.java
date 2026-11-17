package io.neko.workshop.framework.sms.core.client;

import io.neko.workshop.framework.common.exception.ErrorCode;
import io.neko.workshop.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;

import java.util.function.Function;

/**
 * 将 API 的错误码，转换为通用的错误码
 *
 * @see SmsCommonResult
 * @see SmsFrameworkErrorCodeConstants
 *
 * @author 灵喵工坊
 */
public interface SmsCodeMapping extends Function<String, ErrorCode> {
}
