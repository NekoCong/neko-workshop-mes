package io.neko.workshop.module.mes.controller.admin.cla;

import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaMemberQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassMember;
import io.neko.workshop.module.mes.dal.dataobject.user.SysUser;
import io.neko.workshop.module.mes.service.cla.ClassMemberService;
import io.neko.workshop.module.mes.service.user.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/6/27 16:08
 */
@Tag(name = "管理后台 - 班组人员")
@RestController
@RequestMapping("/mes/cal/member")
@RequiredArgsConstructor
public class ClassMemberController {



    private final ClassMemberService classMemberService;

    private final SysUserService sysUserService;



    @PostMapping("/save")
    public CommonResult save(@RequestBody List<ClassMember> classMemberList){
         boolean hasDuplicates = false;
          for(ClassMember classMember:classMemberList){
            if(classMemberService.findMemberByCodeAndName(classMember.getPersonCode(),classMember.getUserName()) != null){
                hasDuplicates = true;
                break;
            }
          }
            for(ClassMember classMember:classMemberList){
                SysUser sysUser =   sysUserService.findUserByIdAndName(classMember.getPersonCode(),classMember.getUserName());
                sysUser.setId(classMember.getId());
                sysUser.setJoinTeam("Y");
                sysUserService.updateSysUser(sysUser);
            }
            if (!hasDuplicates) {
                classMemberService.saveClassMember(classMemberList);
                return success(null).setMsg("新增成功");
            }
         return  success(null);
    }





    @GetMapping("/find")
    public CommonResult<PageResult<ClassMember>> findTeamCode(ClaMemberQueryVo classMemberPageReqVO){
        PageResult<ClassMember> pageResult = classMemberService.selectClassMemberByCode(classMemberPageReqVO);
        return success(pageResult);
    }





    @DeleteMapping("/batch")
    @Operation(summary = "移除班组成员")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        for(Long id:ids){
            SysUser sysUser = sysUserService.findUserById(id);
            sysUser.setJoinTeam("N");
            sysUserService.updateSysUser(sysUser);
        }
        return success(classMemberService.removeByPeopleId(ids)).setMsg("移除人员成功");
    }










}
