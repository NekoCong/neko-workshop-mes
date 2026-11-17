package io.neko.workshop.module.mes.dal.mysql.auto;

import java.util.*;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.dal.dataobject.auto.AutoCodeResult;
import org.apache.ibatis.annotations.Mapper;

/**
 * 编码生成记录 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface AutoCodeResultMapper extends BaseMapperX<AutoCodeResult> {


    /**
     *  编码生成记录集合
     * @param sysAutoCodeResult
     * @return List<AutoCodeResult>
     */
    default  List<AutoCodeResult> selectSysAutoCodeResultList(AutoCodeResult sysAutoCodeResult) {
        return selectList(new LambdaQueryWrapperX<AutoCodeResult>()
                .eqIfPresent(AutoCodeResult::getRuleId, sysAutoCodeResult.getRuleId())
                .eqIfPresent(AutoCodeResult::getLastInputChar, sysAutoCodeResult.getLastInputChar())
                .likeIfPresent(AutoCodeResult::getGenDate, sysAutoCodeResult.getGenDate())
                .orderByDesc(AutoCodeResult::getGenDate));
    }
}
