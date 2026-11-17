package io.neko.workshop.module.mes.service.impl.user;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.user.vo.UserPageReqVo;
import io.neko.workshop.module.mes.dal.dataobject.user.SysUser;
import io.neko.workshop.module.mes.dal.mysql.user.SysUserMapper;
import io.neko.workshop.module.mes.service.user.SysUserService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * 用户实现类
 * @author 灵喵工坊
 * @time 2023/6/27 17:54
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;



    @Override
    public PageResult<SysUser> selectPage(UserPageReqVo userPageReqVO) {
        return sysUserMapper.getSysUserPage(userPageReqVO);
    }


    @Override
    public SysUser findUserByIdAndName(String personCode, String userName) {
        LambdaQueryWrapperX<SysUser> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(SysUser::getId,personCode);
        queryWrapperX.eq(SysUser::getUserName,userName);
        return sysUserMapper.selectOne(queryWrapperX);
    }

    @Override
    public Integer updateSysUser(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser findUserById(Long id) {
        return sysUserMapper.selectById(id);
    }
}
