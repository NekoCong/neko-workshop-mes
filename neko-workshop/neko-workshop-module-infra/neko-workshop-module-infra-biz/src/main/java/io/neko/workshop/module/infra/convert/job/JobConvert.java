package io.neko.workshop.module.infra.convert.job;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.controller.admin.job.vo.job.JobCreateReqVO;
import io.neko.workshop.module.infra.controller.admin.job.vo.job.JobExcelVO;
import io.neko.workshop.module.infra.controller.admin.job.vo.job.JobRespVO;
import io.neko.workshop.module.infra.controller.admin.job.vo.job.JobUpdateReqVO;
import io.neko.workshop.module.infra.dal.dataobject.job.JobDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 定时任务 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface JobConvert {

    JobConvert INSTANCE = Mappers.getMapper(JobConvert.class);

    JobDO convert(JobCreateReqVO bean);

    JobDO convert(JobUpdateReqVO bean);

    JobRespVO convert(JobDO bean);

    List<JobRespVO> convertList(List<JobDO> list);

    PageResult<JobRespVO> convertPage(PageResult<JobDO> page);

    List<JobExcelVO> convertList02(List<JobDO> list);

}
