package io.neko.workshop.module.system.service.sms;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.sms.core.client.SmsClientFactory;
import io.neko.workshop.framework.sms.core.property.SmsChannelProperties;
import io.neko.workshop.module.system.controller.admin.sms.vo.channel.SmsChannelCreateReqVO;
import io.neko.workshop.module.system.controller.admin.sms.vo.channel.SmsChannelPageReqVO;
import io.neko.workshop.module.system.controller.admin.sms.vo.channel.SmsChannelUpdateReqVO;
import io.neko.workshop.module.system.convert.sms.SmsChannelConvert;
import io.neko.workshop.module.system.dal.dataobject.sms.SmsChannelDO;
import io.neko.workshop.module.system.dal.mysql.sms.SmsChannelMapper;
import io.neko.workshop.module.system.mq.producer.sms.SmsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.module.system.enums.ErrorCodeConstants.SMS_CHANNEL_HAS_CHILDREN;
import static io.neko.workshop.module.system.enums.ErrorCodeConstants.SMS_CHANNEL_NOT_EXISTS;

/**
 * 短信渠道 Service 实现类
 *
 * @author zzf
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SmsChannelServiceImpl implements SmsChannelService {

    private final SmsClientFactory smsClientFactory;

    private final SmsChannelMapper smsChannelMapper;

    @Lazy // SmsChannelServiceImpl -> SmsTemplateServiceImpl
    private final SmsTemplateService smsTemplateService;

    private final SmsProducer smsProducer;

    @Override
    @PostConstruct
    public void initLocalCache() {
        // 第一步：查询数据
        List<SmsChannelDO> channels = smsChannelMapper.selectList();
        log.info("[initLocalCache][缓存短信渠道，数量为:{}]", channels.size());

        // 第二步：构建缓存：创建或更新短信 Client
        List<SmsChannelProperties> propertiesList = SmsChannelConvert.INSTANCE.convertList02(channels);
        propertiesList.forEach(properties -> smsClientFactory.createOrUpdateSmsClient(properties));
    }

    @Override
    public Long createSmsChannel(SmsChannelCreateReqVO createReqVO) {
        // 插入
        SmsChannelDO smsChannel = SmsChannelConvert.INSTANCE.convert(createReqVO);
        smsChannelMapper.insert(smsChannel);
        // 发送刷新消息
        smsProducer.sendSmsChannelRefreshMessage();
        // 返回
        return smsChannel.getId();
    }

    @Override
    public void updateSmsChannel(SmsChannelUpdateReqVO updateReqVO) {
        // 校验存在
        validateSmsChannelExists(updateReqVO.getId());
        // 更新
        SmsChannelDO updateObj = SmsChannelConvert.INSTANCE.convert(updateReqVO);
        smsChannelMapper.updateById(updateObj);
        // 发送刷新消息
        smsProducer.sendSmsChannelRefreshMessage();
    }

    @Override
    public void deleteSmsChannel(Long id) {
        // 校验存在
        validateSmsChannelExists(id);
        // 校验是否有在使用该账号的模版
        if (smsTemplateService.countByChannelId(id) > 0) {
            throw exception(SMS_CHANNEL_HAS_CHILDREN);
        }
        // 删除
        smsChannelMapper.deleteById(id);
        // 发送刷新消息
        smsProducer.sendSmsChannelRefreshMessage();
    }

    private void validateSmsChannelExists(Long id) {
        if (smsChannelMapper.selectById(id) == null) {
            throw exception(SMS_CHANNEL_NOT_EXISTS);
        }
    }

    @Override
    public SmsChannelDO getSmsChannel(Long id) {
        return smsChannelMapper.selectById(id);
    }

    @Override
    public List<SmsChannelDO> getSmsChannelList() {
        return smsChannelMapper.selectList();
    }

    @Override
    public PageResult<SmsChannelDO> getSmsChannelPage(SmsChannelPageReqVO pageReqVO) {
        return smsChannelMapper.selectPage(pageReqVO);
    }

}
