package io.neko.workshop.module.member.convert.user;

import io.neko.workshop.module.member.api.user.dto.MemberUserRespDTO;
import io.neko.workshop.module.member.controller.app.user.vo.AppUserInfoRespVO;
import io.neko.workshop.module.member.dal.dataobject.user.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    AppUserInfoRespVO convert(MemberUserDO bean);

    MemberUserRespDTO convert2(MemberUserDO bean);

    List<MemberUserRespDTO> convertList2(List<MemberUserDO> list);

}
