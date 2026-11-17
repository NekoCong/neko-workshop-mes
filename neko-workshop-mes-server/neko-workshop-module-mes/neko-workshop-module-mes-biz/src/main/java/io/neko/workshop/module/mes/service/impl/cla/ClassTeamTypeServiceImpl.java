package io.neko.workshop.module.mes.service.impl.cla;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaTeamTypeExcelVo;
import io.neko.workshop.module.mes.controller.admin.cla.vo.ClaTeamTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.cla.ClassTeamType;
import io.neko.workshop.module.mes.dal.mysql.cla.ClassTeamTypeMapper;
import io.neko.workshop.module.mes.service.cla.ClassTeamTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;


/**
 * 班组类型实现类
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class ClassTeamTypeServiceImpl implements ClassTeamTypeService {

    private final ClassTeamTypeMapper calTeamTypeMapper;



    @Override
    public Integer saveCalTeamType(ClassTeamType calTeamType) {
        return calTeamTypeMapper.insert(calTeamType);
    }

    @Override
    public Integer removeCalTeamTypeByIds(List<Long> ids) {
        return calTeamTypeMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateCalTeamType(ClassTeamType calTeamType) {
        return calTeamTypeMapper.updateById(calTeamType);
    }

    @Override
    public PageResult<ClassTeamType> calTeamTypePage(ClaTeamTypeQueryVo calTeamTypePageReqVO) {
        return calTeamTypeMapper.selectPage(calTeamTypePageReqVO);
    }

    @Override
    public ClassTeamType findCalTeamTypeById(Long id) {
        return calTeamTypeMapper.selectById(id);
    }

    @Override
    public List<ClaTeamTypeExcelVo> exportData() {
        List<ClassTeamType> list = calTeamTypeMapper.selectList();
        ArrayList<ClaTeamTypeExcelVo> excelList = new ArrayList<>(list.size());
        list.forEach(dict -> {
            ClaTeamTypeExcelVo excelDTO = new ClaTeamTypeExcelVo();
            BeanUtils.copyProperties(dict, excelDTO);
            excelList.add(excelDTO);
        });
        return excelList;
    }

    @Override
    public ClassTeamType findCalTeamTypeByName(String typeName) {
        LambdaQueryWrapperX<ClassTeamType> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(ClassTeamType::getTypeName,typeName);
        return calTeamTypeMapper.selectOne(queryWrapperX);
    }


}
