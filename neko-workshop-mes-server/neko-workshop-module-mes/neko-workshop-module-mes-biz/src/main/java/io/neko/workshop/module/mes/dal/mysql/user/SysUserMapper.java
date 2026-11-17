package io.neko.workshop.module.mes.dal.mysql.user;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.user.vo.UserPageReqVo;
import io.neko.workshop.module.mes.dal.dataobject.user.SysUser;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户 Mapper接口
 * @author 灵喵工坊
 * @time 2023/6/27 17:53
 */
@Mapper
public interface SysUserMapper extends BaseMapperX<SysUser> {

    /**
      * 用户条件分页查询
      * @param reqVO
      * @return PageResult<SysUser>
      */
   default PageResult<SysUser> getSysUserPage(UserPageReqVo reqVO){
       LambdaQueryWrapperX<SysUser> queryWrapperX = new LambdaQueryWrapperX<>();
       if(StringUtils.isNotBlank(reqVO.getUsername())){
           queryWrapperX.eq(SysUser::getUserName,reqVO.getUsername());
       }
       if(StringUtils.isNotBlank(reqVO.getUsername())){
           queryWrapperX.isNotNull(SysUser::getId);
       }
       queryWrapperX.eq(SysUser::getJoinTeam, "N");
       queryWrapperX.eq(SysUser::getDeleted, 0);
       return  selectPage(reqVO,queryWrapperX);
   }




}
