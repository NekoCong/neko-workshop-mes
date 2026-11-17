package io.neko.workshop.module.system.convert.tenant;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.system.controller.admin.tenant.vo.packages.TenantPackageCreateReqVO;
import io.neko.workshop.module.system.controller.admin.tenant.vo.packages.TenantPackageRespVO;
import io.neko.workshop.module.system.controller.admin.tenant.vo.packages.TenantPackageSimpleRespVO;
import io.neko.workshop.module.system.controller.admin.tenant.vo.packages.TenantPackageUpdateReqVO;
import io.neko.workshop.module.system.dal.dataobject.tenant.TenantPackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 租户套餐 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface TenantPackageConvert {

    TenantPackageConvert INSTANCE = Mappers.getMapper(TenantPackageConvert.class);

    TenantPackageDO convert(TenantPackageCreateReqVO bean);

    TenantPackageDO convert(TenantPackageUpdateReqVO bean);

    TenantPackageRespVO convert(TenantPackageDO bean);

    List<TenantPackageRespVO> convertList(List<TenantPackageDO> list);

    PageResult<TenantPackageRespVO> convertPage(PageResult<TenantPackageDO> page);

    List<TenantPackageSimpleRespVO> convertList02(List<TenantPackageDO> list);

}
