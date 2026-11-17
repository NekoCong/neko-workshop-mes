package io.neko.workshop.module.system.controller.admin.mail;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.mail.vo.template.*;
import io.neko.workshop.module.system.convert.mail.MailTemplateConvert;
import io.neko.workshop.module.system.dal.dataobject.mail.MailTemplateDO;
import io.neko.workshop.module.system.service.mail.MailSendService;
import io.neko.workshop.module.system.service.mail.MailTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;

@Tag(name = "管理后台 - 邮件模版")
@RestController
@RequestMapping("/system/mail-template")
@RequiredArgsConstructor
public class MailTemplateController {

    private final MailTemplateService mailTempleService;
    private final MailSendService mailSendService;

    @PostMapping("/create")
    @Operation(summary = "创建邮件模版")
    @PreAuthorize("@ss.hasPermission('system:mail-template:create')")
    public CommonResult<Long> createMailTemplate(@Valid @RequestBody MailTemplateCreateReqVO createReqVO){
        return success(mailTempleService.createMailTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "修改邮件模版")
    @PreAuthorize("@ss.hasPermission('system:mail-template:update')")
    public CommonResult<Boolean> updateMailTemplate(@Valid @RequestBody MailTemplateUpdateReqVO updateReqVO){
        mailTempleService.updateMailTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除邮件模版")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:mail-template:delete')")
    public CommonResult<Boolean> deleteMailTemplate(@RequestParam("id") Long id) {
        mailTempleService.deleteMailTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得邮件模版")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:mail-template:get')")
    public CommonResult<MailTemplateRespVO> getMailTemplate(@RequestParam("id") Long id) {
        MailTemplateDO mailTemplateDO = mailTempleService.getMailTemplate(id);
        return success(MailTemplateConvert.INSTANCE.convert(mailTemplateDO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得邮件模版分页")
    @PreAuthorize("@ss.hasPermission('system:mail-template:query')")
    public CommonResult<PageResult<MailTemplateRespVO>> getMailTemplatePage(@Valid MailTemplatePageReqVO pageReqVO) {
        PageResult<MailTemplateDO> pageResult = mailTempleService.getMailTemplatePage(pageReqVO);
        return success(MailTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获得邮件模版精简列表")
    public CommonResult<List<MailTemplateSimpleRespVO>> getSimpleTemplateList() {
        List<MailTemplateDO> list = mailTempleService.getMailTemplateList();
        return success(MailTemplateConvert.INSTANCE.convertList02(list));
    }

    @PostMapping("/send-mail")
    @Operation(summary = "发送短信")
    @PreAuthorize("@ss.hasPermission('system:mail-template:send-mail')")
    public CommonResult<Long> sendMail(@Valid @RequestBody MailTemplateSendReqVO sendReqVO) {
        return success(mailSendService.sendSingleMailToAdmin(sendReqVO.getMail(), null,
                sendReqVO.getTemplateCode(), sendReqVO.getTemplateParams()));
    }

}
