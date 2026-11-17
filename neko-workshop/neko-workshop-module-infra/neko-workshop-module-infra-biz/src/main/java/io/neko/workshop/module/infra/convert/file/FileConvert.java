package io.neko.workshop.module.infra.convert.file;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.controller.admin.file.vo.file.FileRespVO;
import io.neko.workshop.module.infra.dal.dataobject.file.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileRespVO convert(FileDO bean);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

}
