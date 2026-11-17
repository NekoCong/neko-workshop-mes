package io.neko.workshop.module.mes.dal.mysql.data;

import io.neko.workshop.framework.mybatis.core.mapper.BaseMapperX;
import io.neko.workshop.module.mes.dal.dataobject.data.CustomDictData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典 Mapper
 * @author 灵喵工坊
 */
@Mapper
public interface CustomDictDataMapper extends BaseMapperX<CustomDictData> {
}
