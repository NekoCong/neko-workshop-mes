package io.neko.workshop.module.mes.dal.mysql.dept;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.dal.dataobject.dept.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 灵喵工坊
 * @time 2023/7/7 15:01
 */
@Mapper
public interface SysDeptMapper extends BaseMapperX<SysDept> {

   default SysDept findDeptByName(String department){
       LambdaQueryWrapperX<SysDept> queryWrapperX = new LambdaQueryWrapperX<>();
       queryWrapperX.eq(SysDept::getName,department);
       return  selectOne(queryWrapperX);
   }


}
