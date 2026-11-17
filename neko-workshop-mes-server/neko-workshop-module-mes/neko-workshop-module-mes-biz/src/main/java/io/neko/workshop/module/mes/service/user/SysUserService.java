package io.neko.workshop.module.mes.service.user;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.user.vo.UserPageReqVo;
import io.neko.workshop.module.mes.dal.dataobject.user.SysUser;



/**
 * @author 灵喵工坊
 * @time 2023/6/27 17:54
 */
public interface SysUserService {


    /**
     * 用户条件分页列表
     * @param userPageReqVO
     * @return  PageResult<SysUser>
     */
    PageResult<SysUser> selectPage(UserPageReqVo userPageReqVO);

    /**
     * 根据用户id用户名查询用户信息
     * @param personCode
     * @param userName
     * @return SysUser
     */
    SysUser findUserByIdAndName(String personCode, String userName);

    /**
     * 修改用户
     * @param sysUser
     * @return Integer
     */
    Integer updateSysUser(SysUser sysUser);

    /**
     * 根据id查询用户信息
     * @param id
     * @return SysUser
     */
    SysUser findUserById(Long id);
}
