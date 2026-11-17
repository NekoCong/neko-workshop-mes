package io.neko.workshop.module.system.api.notify;

import io.neko.workshop.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import io.neko.workshop.module.system.service.notify.NotifySendService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 站内信发送 API 实现类
 *
 * @author xrcoder
 */
@Service
@RequiredArgsConstructor
public class NotifyMessageSendApiImpl implements NotifyMessageSendApi {

    private final NotifySendService notifySendService;

    @Override
    public Long sendSingleMessageToAdmin(NotifySendSingleToUserReqDTO reqDTO) {
        return notifySendService.sendSingleNotifyToAdmin(reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    @Override
    public Long sendSingleMessageToMember(NotifySendSingleToUserReqDTO reqDTO) {
        return notifySendService.sendSingleNotifyToMember(reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}
