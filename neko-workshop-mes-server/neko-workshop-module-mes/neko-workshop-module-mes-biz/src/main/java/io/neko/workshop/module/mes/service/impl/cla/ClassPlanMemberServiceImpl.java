package io.neko.workshop.module.mes.service.impl.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaPlanMemberQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassPlanMember;
import io.neko.workshop.module.mes.dal.mysql.cla.ClassPlanMemberMapper;
import io.neko.workshop.module.mes.service.cla.ClassPlanMemberService;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/6/30 13:48
 */
@Service
@RequiredArgsConstructor
public class ClassPlanMemberServiceImpl implements ClassPlanMemberService {

    private final ClassPlanMemberMapper classPlanMemberMapper;



    @Override
    public Integer saveClassPlanMember(ClassPlanMember classPlanMember) {
        return classPlanMemberMapper.insert(classPlanMember);
    }


    @Override
    public PageResult<ClassPlanMember> classPlanMemberPage(ClaPlanMemberQueryVo classPlanMemberPageReqVO) {
        return classPlanMemberMapper.findClassPlanMemberPage(classPlanMemberPageReqVO);
    }

    @Override
    public Integer removeByPlanCode(String planCode) {
        LambdaQueryWrapperX<ClassPlanMember> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.in(ClassPlanMember::getPlanCode,planCode);
        return classPlanMemberMapper.delete(queryWrapperX);
    }

    @Override
    public ClassPlanMember findByPeopleIdAndName( String planCode,Long peopleId, String teamPeople) {
        LambdaQueryWrapperX<ClassPlanMember> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ClassPlanMember::getPlanCode,planCode);
        queryWrapperX.eq(ClassPlanMember::getPeopleId,peopleId);
        queryWrapperX.eq(ClassPlanMember::getPlanPeopleName,teamPeople);
        return classPlanMemberMapper.selectOne(queryWrapperX);
    }

    @Override
    public List<ClassPlanMember> findAll() {
        return classPlanMemberMapper.selectList();
    }

    @Override
    public List<ClassPlanMember> findByTypeAndCodeAndName(String teamType, String teamCode, String teamName) {
        LambdaQueryWrapperX<ClassPlanMember> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ClassPlanMember::getTeamType,teamType);
        queryWrapperX.eq(ClassPlanMember::getTeamCode,teamCode);
        queryWrapperX.eq(ClassPlanMember::getTeamName,teamName);
        return classPlanMemberMapper.selectList(queryWrapperX);
    }

    @Override
    public Integer removeClassPlanMemberById(Long memberId) {
        return classPlanMemberMapper.deleteById(memberId);
    }

    @Override
    public List<ClassPlanMember> findClassPlanMember(Long id) {
        LambdaQueryWrapperX<ClassPlanMember> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ClassPlanMember::getId,id);
        return classPlanMemberMapper.selectList(queryWrapperX);
    }

    @Override
    public ClassPlanMember findPlanMemberByPeopleId(Long memberId) {
        LambdaQueryWrapperX<ClassPlanMember> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ClassPlanMember::getPeopleId,memberId);
        return classPlanMemberMapper.selectOne(queryWrapperX);
    }

    @Override
    public Integer updateClassPlanMember(ClassPlanMember classPlanMember1) {
        return classPlanMemberMapper.updateById(classPlanMember1);
    }


}
