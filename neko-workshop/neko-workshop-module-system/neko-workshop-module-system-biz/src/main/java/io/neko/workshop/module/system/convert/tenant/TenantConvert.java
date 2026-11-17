package io.neko.workshop.module.system.convert.tenant;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.tenant.vo.tenant.TenantCreateReqVO;
import io.neko.workshop.module.system.controller.admin.tenant.vo.tenant.TenantExcelVO;
import io.neko.workshop.module.system.controller.admin.tenant.vo.tenant.TenantRespVO;
import io.neko.workshop.module.system.controller.admin.tenant.vo.tenant.TenantUpdateReqVO;
import io.neko.workshop.module.system.controller.admin.user.vo.user.UserCreateReqVO;
import io.neko.workshop.module.system.dal.dataobject.tenant.TenantDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 租户 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface TenantConvert {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    TenantDO convert(TenantCreateReqVO bean);

    TenantDO convert(TenantUpdateReqVO bean);

    TenantRespVO convert(TenantDO bean);

    List<TenantRespVO> convertList(List<TenantDO> list);

    PageResult<TenantRespVO> convertPage(PageResult<TenantDO> page);

    List<TenantExcelVO> convertList02(List<TenantDO> list);

    default UserCreateReqVO convert02(TenantCreateReqVO bean) {
        UserCreateReqVO reqVO = new UserCreateReqVO();
        reqVO.setUsername(bean.getUsername());
        reqVO.setPassword(bean.getPassword());
        reqVO.setNickname(bean.getContactName()).setMobile(bean.getContactMobile());
        return reqVO;
    }

}
