package io.neko.workshop.module.infra.convert.db;

import java.util.*;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import io.neko.workshop.module.infra.controller.admin.db.vo.*;
import io.neko.workshop.module.infra.dal.dataobject.db.DataSourceConfigDO;

/**
 * 数据源配置 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface DataSourceConfigConvert {

    DataSourceConfigConvert INSTANCE = Mappers.getMapper(DataSourceConfigConvert.class);

    DataSourceConfigDO convert(DataSourceConfigCreateReqVO bean);

    DataSourceConfigDO convert(DataSourceConfigUpdateReqVO bean);

    DataSourceConfigRespVO convert(DataSourceConfigDO bean);

    List<DataSourceConfigRespVO> convertList(List<DataSourceConfigDO> list);

}
