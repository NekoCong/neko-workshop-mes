package io.neko.workshop.module.mes.service.impl.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.WorkShopQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.WorkShop;
import io.neko.workshop.module.mes.dal.mysql.md.WorkShopMapper;
import io.neko.workshop.module.mes.service.md.WorkShopService;
import org.springframework.stereotype.Service;
import java.util.*;
import lombok.RequiredArgsConstructor;



/**
 * 车间 Service 实现类
 * @author 灵喵工坊MES
 */
@Service
@RequiredArgsConstructor
public class WorkShopServiceImpl implements WorkShopService {

    private final WorkShopMapper workshopMapper;


    @Override
    public Integer insertWorkshopDO(WorkShop workshop) {
        workshop.setCreateTime(new Date());
        return workshopMapper.insert(workshop);
    }

    @Override
    public Integer removeWorkshop(List<Long> ids) {
       return  workshopMapper.deleteBatchIds(ids);
    }


    @Override
    public Integer updateWorkshops(WorkShop Workshop) {
        Workshop.setUpdateTime(new Date());
        Workshop.setId(Workshop.getId());
        return workshopMapper.updateById(Workshop);
    }



    @Override
    public WorkShop selectMdWorkshopByWorkshopId(Long workshopId) {
        LambdaQueryWrapperX<WorkShop> queryWrapper = new LambdaQueryWrapperX<>();
        queryWrapper.eq(WorkShop::getId,workshopId);
        return workshopMapper.selectOne(queryWrapper);
    }


    @Override
    public PageResult<WorkShop> listWorkshopPage(WorkShopQueryVo workshopPage) {
        return workshopMapper.listWorkshopPage(workshopPage);
    }

    @Override
    public WorkShop findWorkshopById(Long id) {
        return workshopMapper.selectById(id);
    }

    @Override
    public WorkShop findByCodeAndName(String workshopCode, String workshopName) {
        LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(WorkShop::getWorkshopCode,workshopCode);
        queryWrapperX.eq(WorkShop::getWorkshopName,workshopName);
        return workshopMapper.selectOne(queryWrapperX);
    }

    @Override
    public WorkShop findWorkShopByCode(String workshopCode) {
        LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(WorkShop::getWorkshopCode,workshopCode);
        return workshopMapper.selectOne(queryWrapperX);
    }

    @Override
    public WorkShop findWorkShopByName(String workshopName) {
        LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(WorkShop::getWorkshopName,workshopName);
        return workshopMapper.selectOne(queryWrapperX);
    }

    @Override
    public List<WorkShop> findavailableList() {
        LambdaQueryWrapperX<WorkShop> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(WorkShop::getEnableFlag,"Y");
        return workshopMapper.selectList(queryWrapperX);
    }

    @Override
    public List<WorkShop> selectMdWorkshopList(WorkShop mdWorkshop) {
        return workshopMapper.selectList();
    }

}
