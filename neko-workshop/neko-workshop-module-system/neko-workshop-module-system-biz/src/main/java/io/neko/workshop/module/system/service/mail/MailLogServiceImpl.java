package io.neko.workshop.module.system.service.mail;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.mail.vo.log.MailLogPageReqVO;
import io.neko.workshop.module.system.dal.dataobject.mail.MailAccountDO;
import io.neko.workshop.module.system.dal.dataobject.mail.MailLogDO;
import io.neko.workshop.module.system.dal.dataobject.mail.MailTemplateDO;
import io.neko.workshop.module.system.dal.mysql.mail.MailLogMapper;
import io.neko.workshop.module.system.enums.mail.MailSendStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static cn.hutool.core.exceptions.ExceptionUtil.getRootCauseMessage;
import lombok.RequiredArgsConstructor;

/**
 * 邮件日志 Service 实现类
 *
 * @author wangjingyi
 * @since 2022-03-21
 */
@Service
@Validated
@RequiredArgsConstructor
public class MailLogServiceImpl implements MailLogService {

    private final MailLogMapper mailLogMapper;

    @Override
    public PageResult<MailLogDO> getMailLogPage(MailLogPageReqVO pageVO) {
        return mailLogMapper.selectPage(pageVO);
    }

    @Override
    public MailLogDO getMailLog(Long id) {
        return mailLogMapper.selectById(id);
    }

    @Override
    public Long createMailLog(Long userId, Integer userType, String toMail,
                              MailAccountDO account, MailTemplateDO template,
                              String templateContent, Map<String, Object> templateParams, Boolean isSend) {
        MailLogDO.MailLogDOBuilder logDOBuilder = MailLogDO.builder();
        // 根据是否要发送，设置状态
        logDOBuilder.sendStatus(Objects.equals(isSend, true) ? MailSendStatusEnum.INIT.getStatus()
                : MailSendStatusEnum.IGNORE.getStatus())
                // 用户信息
                .userId(userId).userType(userType).toMail(toMail)
                .accountId(account.getId()).fromMail(account.getMail())
                // 模板相关字段
                .templateId(template.getId()).templateCode(template.getCode()).templateNickname(template.getNickname())
                .templateTitle(template.getTitle()).templateContent(templateContent).templateParams(templateParams);

        // 插入数据库
        MailLogDO logDO = logDOBuilder.build();
        mailLogMapper.insert(logDO);
        return logDO.getId();
    }

    @Override
    public void updateMailSendResult(Long logId, String messageId, Exception exception) {
        // 1. 成功
        if (exception == null) {
            mailLogMapper.updateById(new MailLogDO().setId(logId).setSendTime(new Date())
                    .setSendStatus(MailSendStatusEnum.SUCCESS.getStatus()).setSendMessageId(messageId));
            return;
        }
        // 2. 失败
        mailLogMapper.updateById(new MailLogDO().setId(logId).setSendTime(new Date())
                .setSendStatus(MailSendStatusEnum.FAILURE.getStatus()).setSendException(getRootCauseMessage(exception)));

    }

}
