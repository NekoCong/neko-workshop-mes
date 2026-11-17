package io.neko.workshop.module.mes.dal.mysql.pro;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.controller.admin.pro.vo.ProcessQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.Process;
import io.neko.workshop.module.mes.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 生产工序 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface ProcessMapper extends BaseMapperX<Process> {


    /**
     * 工序条件分页查询
     * @param reqVO
     * @return PageResult<Process>
     */
    default PageResult<Process> selectPage(ProcessQueryVo reqVO) {
        LambdaQueryWrapperX<Process> queryWrapper = new LambdaQueryWrapperX<>();
        if(StringUtils.isNotBlank(reqVO.getProcessCode())){
            queryWrapper.eq(Process::getProcessCode,reqVO.getProcessCode());
        }
        if(StringUtils.isNotBlank(reqVO.getProcessName())){
            queryWrapper.like(Process::getProcessName,reqVO.getProcessName());
        }
        if(StringUtils.isNotBlank(reqVO.getEnableFlag())){
            queryWrapper.eq(Process::getEnableFlag,reqVO.getEnableFlag());
        }
        if(StringUtils.isBlank(reqVO.getProcessCode()) && StringUtils.isBlank(reqVO.getProcessName()) && StringUtils.isBlank(reqVO.getEnableFlag())){
            queryWrapper.isNotNull(Process::getId);
        }
        return selectPage(reqVO,queryWrapper);
    }


    /**
     * 根据工序编号和名称查询工序信息
     * @param processCode
     * @param processName
     * @return Process
     */
   default Process selectByCodeAndName(String processCode, String processName){
       LambdaQueryWrapperX<Process> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(Process::getProcessCode,processCode);
       queryWrapperX.eq(Process::getProcessName,processName);
       return  selectOne(queryWrapperX);
   }

    default Process findProcessByName( String processName){
        LambdaQueryWrapperX<Process> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Process::getProcessName,processName);
        return  selectOne(queryWrapperX);
    }

}
