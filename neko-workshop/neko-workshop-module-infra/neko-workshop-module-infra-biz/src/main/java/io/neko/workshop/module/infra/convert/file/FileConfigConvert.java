package io.neko.workshop.module.infra.convert.file;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.controller.admin.file.vo.config.FileConfigCreateReqVO;
import io.neko.workshop.module.infra.controller.admin.file.vo.config.FileConfigRespVO;
import io.neko.workshop.module.infra.controller.admin.file.vo.config.FileConfigUpdateReqVO;
import io.neko.workshop.module.infra.dal.dataobject.file.FileConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文件配置 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface FileConfigConvert {

    FileConfigConvert INSTANCE = Mappers.getMapper(FileConfigConvert.class);

    @Mapping(target = "config", ignore = true)
    FileConfigDO convert(FileConfigCreateReqVO bean);

    @Mapping(target = "config", ignore = true)
    FileConfigDO convert(FileConfigUpdateReqVO bean);

    FileConfigRespVO convert(FileConfigDO bean);

    List<FileConfigRespVO> convertList(List<FileConfigDO> list);

    PageResult<FileConfigRespVO> convertPage(PageResult<FileConfigDO> page);

}
