package io.neko.workshop.module.mes.dal.mysql.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.WorkstationMachineQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.WorkStationMachine;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作站设备资源 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface WorkStationMachineMapper extends BaseMapperX<WorkStationMachine> {

    /**
     * 工作站设备资源条件分页查询
     * @param workstationMachine
     * @return PageResult<WorkstationMachine>
     */
    default PageResult<WorkStationMachine> selectPage(WorkstationMachineQueryVo workstationMachine) {
        LambdaQueryWrapperX<WorkStationMachine> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotNull(workstationMachine.getWorkstationId())){
            queryWrapper.eq(WorkStationMachine::getWorkstationId,workstationMachine.getWorkstationId());
        }
        return  selectPage(workstationMachine,queryWrapper);
    }


    /**
     * 根据id查询工作站设备资源
     * @param id
     * @return WorkstationMachine
     */
   default WorkStationMachine selectByWorkstationId(Long id){
       LambdaQueryWrapperX<WorkStationMachine> queryWrapper = new LambdaQueryWrapperX<>();
       queryWrapper.eq(WorkStationMachine::getWorkstationId,id);
       return  selectOne(queryWrapper);
   }




}
