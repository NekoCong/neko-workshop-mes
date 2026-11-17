package io.neko.workshop.module.mes.service.impl.pro;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.pro.vo.*;
import io.neko.workshop.module.mes.dal.dataobject.pro.Route;
import io.neko.workshop.module.mes.dal.mysql.pro.RouteMapper;
import io.neko.workshop.module.mes.service.pro.RouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.*;
import lombok.RequiredArgsConstructor;




/**
 * 工艺路线 Service 实现类
 * @author 灵喵工坊MES
 */
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;


    @Override
    public Route getRoute(Long id) {
        return routeMapper.selectById(id);
    }

//    @Override
//    public List<Route> getRouteList(Collection<Long> ids) {
//        return routeMapper.selectBatchIds(ids);
//    }

    @Override
    public PageResult<Route> getRoutePage(RoutePageReqVo pageReqVO) {
        return routeMapper.selectPage(pageReqVO);
    }


    @Override
    public Integer insertRoute(Route routeDO) {
        routeDO.setCreateTime(new Date());
        return routeMapper.insert(routeDO);
    }

    @Override
    public Integer updateRoutes(Route routeDO) {
        routeDO.setUpdateTime(new Date());
        return routeMapper.updateById(routeDO);
    }

    @Override
    public Integer deleteRoutes( List<Long> routeIds) {
        return routeMapper.deleteBatchIds(routeIds);
    }



    @Override
    public List<RouteExcelVo> listData() {
        List<Route> dictList = routeMapper.selectList();
        ArrayList<RouteExcelVo> excelDictDTOList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            RouteExcelVo excelDictDTO = new RouteExcelVo();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });
        return excelDictDTOList;
    }



    @Override
    public Route findRouteByCode(String routeCode) {
        LambdaQueryWrapperX<Route> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Route::getRouteCode,routeCode);
        return routeMapper.selectOne(queryWrapperX);
    }



}
