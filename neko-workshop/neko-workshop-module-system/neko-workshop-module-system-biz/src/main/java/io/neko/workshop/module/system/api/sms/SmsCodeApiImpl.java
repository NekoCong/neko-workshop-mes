package io.neko.workshop.module.system.api.sms;

import io.neko.workshop.module.system.api.sms.dto.code.SmsCodeValidateReqDTO;
import io.neko.workshop.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import io.neko.workshop.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import io.neko.workshop.module.system.service.sms.SmsCodeService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

/**
 * 短信验证码 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class SmsCodeApiImpl implements SmsCodeApi {

    private final SmsCodeService smsCodeService;

    @Override
    public void sendSmsCode(SmsCodeSendReqDTO reqDTO) {
        smsCodeService.sendSmsCode(reqDTO);
    }

    @Override
    public void useSmsCode(SmsCodeUseReqDTO reqDTO) {
        smsCodeService.useSmsCode(reqDTO);
    }

    @Override
    public void validateSmsCode(SmsCodeValidateReqDTO reqDTO) {
        smsCodeService.validateSmsCode(reqDTO);
    }

}
