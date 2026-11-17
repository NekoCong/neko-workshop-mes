package io.neko.workshop.module.member.convert.social;

import io.neko.workshop.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import io.neko.workshop.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import io.neko.workshop.module.system.api.social.dto.SocialUserBindReqDTO;
import io.neko.workshop.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppSocialUserBindReqVO reqVO);

    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

}
