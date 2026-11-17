package io.neko.workshop.module.mes.service.impl.md;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.UnitMeasureExcelVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.UnitMeasureQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.UnitMeasure;
import io.neko.workshop.module.mes.dal.mysql.md.UnitMeasureMapper;
import io.neko.workshop.module.mes.service.md.UnitMeasureService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.*;
import io.neko.workshop.framework.common.pojo.PageResult;
import lombok.RequiredArgsConstructor;




/**
 * 单位 Service 实现类
 * @author 灵喵工坊MES
 */
@Service
@RequiredArgsConstructor
public class UnitMeasureServiceImpl implements UnitMeasureService {


    private final UnitMeasureMapper unitMeasureMapper;


    @Override
    public Integer createUnitMeasure(UnitMeasure measure) {
        return  unitMeasureMapper.insert(measure);
    }


    @Override
    public Integer updateUnitMeasure(UnitMeasure measure) {
        measure.setUpdateTime(new Date());
      return  unitMeasureMapper.updateById(measure);
    }




    @Override
    public UnitMeasure getUnitMeasure(Long id) {
        return unitMeasureMapper.selectById(id);
    }



    @Override
    public PageResult<UnitMeasure> getUnitMeasurePage(UnitMeasureQueryVo pageReqVO) {
        return unitMeasureMapper.selectPage(pageReqVO);
    }



    @Override
    public UnitMeasure selectMdUnitConversionNameByCode(String unitCode) {
        QueryWrapper<UnitMeasure> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("measure_code",unitCode);
        return unitMeasureMapper.selectOne(queryWrapper);
    }



    @Override
    public Integer removeUnitMeasures(List<Long> ids) {
        return unitMeasureMapper.deleteBatchIds(ids);
    }



    @Override
    public List<UnitMeasureExcelVo> listData() {
        List<UnitMeasure> dictList = unitMeasureMapper.selectList();
        ArrayList<UnitMeasureExcelVo> excelDictDTOList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            UnitMeasureExcelVo excelDictDTO = new UnitMeasureExcelVo();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });
        return excelDictDTOList;
    }



    @Override
    public List<UnitMeasure> findMeasureNameByMeasureCode(String measureCode) {
        QueryWrapper<UnitMeasure> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("measure_code",measureCode);
        return unitMeasureMapper.selectList(queryWrapper);
    }

    @Override
    public List<UnitMeasure> selectMdUnitMeasureList() {
        LambdaQueryWrapperX<UnitMeasure> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(UnitMeasure::getEnableFlag,"Y");
        queryWrapperX.orderByAsc(UnitMeasure::getMeasureCode);
        return unitMeasureMapper.selectList(queryWrapperX);
    }


    @Override
    public UnitMeasure selectByUnitOfMeasure(String unitOfMeasure) {
        LambdaQueryWrapperX<UnitMeasure> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(UnitMeasure::getMeasureName,unitOfMeasure);
        return unitMeasureMapper.selectOne(queryWrapperX);
    }



    @Override
    public List<UnitMeasure> getEnableList() {
        LambdaQueryWrapperX<UnitMeasure> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(UnitMeasure::getEnableFlag,"Y");
        return unitMeasureMapper.selectList(queryWrapperX);
    }




}
