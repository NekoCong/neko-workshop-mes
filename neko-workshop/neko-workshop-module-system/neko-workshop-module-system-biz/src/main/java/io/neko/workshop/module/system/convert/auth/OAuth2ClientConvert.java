package io.neko.workshop.module.system.convert.auth;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.client.OAuth2ClientCreateReqVO;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.client.OAuth2ClientRespVO;
import io.neko.workshop.module.system.controller.admin.oauth2.vo.client.OAuth2ClientUpdateReqVO;
import io.neko.workshop.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * OAuth2 客户端 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface OAuth2ClientConvert {

    OAuth2ClientConvert INSTANCE = Mappers.getMapper(OAuth2ClientConvert.class);

    OAuth2ClientDO convert(OAuth2ClientCreateReqVO bean);

    OAuth2ClientDO convert(OAuth2ClientUpdateReqVO bean);

    OAuth2ClientRespVO convert(OAuth2ClientDO bean);

    List<OAuth2ClientRespVO> convertList(List<OAuth2ClientDO> list);

    PageResult<OAuth2ClientRespVO> convertPage(PageResult<OAuth2ClientDO> page);

}
