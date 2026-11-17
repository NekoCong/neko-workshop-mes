package io.neko.workshop.module.mes.service.impl.issue;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueType;
import io.neko.workshop.module.mes.dal.mysql.issue.IssueTypeMapper;
import io.neko.workshop.module.mes.service.issue.IssueTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.service.impl.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 09:39
 */
@Service
@RequiredArgsConstructor
public class IssueTypeServiceImpl implements IssueTypeService {

    private final IssueTypeMapper issueTypeMapper;

    @Override
    public Integer saveIssueType(IssueType issueType) {
        return issueTypeMapper.insert(issueType);
    }

    @Override
    public Integer removeIssueType(List<Long> ids) {
        return issueTypeMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateIssueType(IssueType issueType) {
        return issueTypeMapper.updateById(issueType);
    }

    @Override
    public IssueType findIssueTypeById(Long id) {
        return issueTypeMapper.selectById(id);
    }

    @Override
    public PageResult<IssueType> findPage(IssueTypeQueryVo issueTypeQueryVo) {
        return issueTypeMapper.findPage(issueTypeQueryVo);
    }

    @Override
    public IssueType findByName(String name) {
        return issueTypeMapper.selectOne(new LambdaQueryWrapperX<IssueType>().eq(IssueType::getName, name));
    }

    /*@Override
    public List<TypeNameAndModeNameListResponseVO> selectAllTypeNameWithModeNameList() {

        List<TypeNameAndModeNameListResponseVO> typeNameAndModeNameListResponseVOList = new ArrayList<>();

        //遍历SQL表
        for (TypeAndModeVO typeAndModeVO : issueTypeMapper.selectAllTypeNameWithModeNameList()) {
            //判断是否已经添加过相同typeName的TypeAndModeResponseVO
            if (typeNameAndModeNameListResponseVOList.stream().noneMatch(n -> n.getTypeName().equals(typeAndModeVO.getTypeName()))) {
                //将SQL值赋值给typeAndModeResponseVOList的typeName同时初始化modeList
                typeNameAndModeNameListResponseVOList.add(new TypeNameAndModeNameListResponseVO()
                                                   .setTypeName(typeAndModeVO.getTypeName())
                                                    .setModeNameList(new ArrayList<>()));
            }
        }

        //遍历typeAndModeResponseVOList
        for (TypeNameAndModeNameListResponseVO typeNameAndModeNameListResponseVO : typeNameAndModeNameListResponseVOList) {
            //遍历SQL表
            for (TypeAndModeVO typeAndModeVO : issueTypeMapper.selectAllTypeNameWithModeNameList()) {
                //如果typeAndModeResponseVOList中的typeName和SQL中的相同 且 SQL中的modeName不为空
                if (typeNameAndModeNameListResponseVO.getTypeName().equals(typeAndModeVO.getTypeName()) && (typeAndModeVO.getModeName()!=null)) {
                    //将SQL中的modeName加入typeAndModeResponseVOList的modeList中
                    typeNameAndModeNameListResponseVO.getModeNameList().add(typeAndModeVO.getModeName());
                }
            }
        }

        return typeNameAndModeNameListResponseVOList;
    }*/

}
