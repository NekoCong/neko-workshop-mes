package io.neko.workshop.module.mes.service.impl.dv;


import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.common.TreeSelect;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryTypeQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.MachineryType;
import io.neko.workshop.module.mes.dal.mysql.dv.MachineryTypeMapper;
import io.neko.workshop.module.mes.service.dv.MachineryTypeService;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import io.neko.workshop.framework.common.pojo.PageResult;
import lombok.RequiredArgsConstructor;


/**
 * 设备类型 Service 实现类
 * @author 灵喵工坊MES
 */
@Service
@RequiredArgsConstructor
public class MachineryTypeServiceImpl implements MachineryTypeService {

    private final MachineryTypeMapper machineryTypeMapper;
    private List<MachineryType> machineryTypeDOList;

    @Override
    public Integer createMachineryType(MachineryType machineryTypeCreateReqVO) {
        if(machineryTypeCreateReqVO.getParentTypeId()!= null) {
            MachineryType parent = machineryTypeMapper.selectMachineryTypeByMachineryTypeId(machineryTypeCreateReqVO.getParentTypeId());
            if (StringUtils.isNotNull(parent)) {
                machineryTypeCreateReqVO.setAncestors(parent.getAncestors() + "," + parent.getId());
            }
        }
        machineryTypeCreateReqVO.setCreateTime(new Date());
        return  machineryTypeMapper.insert(machineryTypeCreateReqVO);

    }

    @Override
    public Integer updateMachineryType(MachineryType machineryTypeDO) {
        MachineryType machineryTypeCreateTime = machineryTypeMapper.selectById(machineryTypeDO.getId());
        machineryTypeDO.setCreateTime(machineryTypeCreateTime.getCreateTime());
        machineryTypeDO.setUpdateTime(new Date());
        return machineryTypeMapper.updateById(machineryTypeDO);
    }



    @Override
    public MachineryType getMachineryType(Long id) {
        return machineryTypeMapper.selectById(id);
    }




    @Override
    public PageResult<MachineryType> getMachineryTypePage(MachineryTypeQueryVo pageReqVO) {
        pageReqVO.setPageSize(100000);
        return machineryTypeMapper.selectPage(pageReqVO);
    }




    @Override
    public List<MachineryType> findItemTypeList(MachineryType machineryTypeDO) {
        return machineryTypeMapper.findItemTypeList(machineryTypeDO);
    }


    /**
     * 树形显示业务
     */
    @Override
    public List<TreeSelect> buildTreeSelect(List<MachineryType> machineryTypeList) {
        List<MachineryType> machineryTypeDOList = buildTree(machineryTypeList);
        return machineryTypeDOList.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    @Override
    public Integer deleteDvMachineryTypeByMachineryTypeId(Long machineryTypeIds) {
        return machineryTypeMapper.deleteById(machineryTypeIds);
    }

    @Override
    public MachineryType findByCodeAndName(String machineryTypeCode, String machineryTypeName) {
        LambdaQueryWrapperX<MachineryType> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(MachineryType::getMachineryTypeCode,machineryTypeCode);
        queryWrapperX.eq(MachineryType::getMachineryTypeName,machineryTypeName);
        return machineryTypeMapper.selectOne(queryWrapperX);
    }


    private List<MachineryType> buildTree(List<MachineryType> machineryTypeDOList){
        this.machineryTypeDOList = machineryTypeDOList;
        List<MachineryType> returnList = new ArrayList<MachineryType>();
        List<Long> tempList = new ArrayList<Long>();
        for(MachineryType it : machineryTypeDOList){
            tempList.add(it.getId());
        }

        for(MachineryType it : machineryTypeDOList){
            if(!tempList.contains(it.getParentTypeId())){
                recursionFn(machineryTypeDOList,it);
                returnList.add(it);
            }
        }
        if(returnList.isEmpty()){
            returnList = machineryTypeDOList;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<MachineryType> list, MachineryType t)
    {
        // 得到子节点列表
        List<MachineryType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (MachineryType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<MachineryType> getChildList(List<MachineryType> list, MachineryType t)
    {
        List<MachineryType> tlist = new ArrayList<MachineryType>();
        Iterator<MachineryType> it = list.iterator();
        while (it.hasNext())
        {
            MachineryType n = (MachineryType) it.next();
            if (StringUtils.isNotNull(n.getParentTypeId()) && n.getParentTypeId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }



    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MachineryType> list, MachineryType t)
    {
        return getChildList(list, t).size() > 0;
    }



}
