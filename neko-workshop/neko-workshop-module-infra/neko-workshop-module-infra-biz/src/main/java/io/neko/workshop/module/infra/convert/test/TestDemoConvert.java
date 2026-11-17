package io.neko.workshop.module.infra.convert.test;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoCreateReqVO;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoExcelVO;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoRespVO;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoUpdateReqVO;
import io.neko.workshop.module.infra.dal.dataobject.test.TestDemoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型 Convert
 *
 * @author 灵喵工坊
 */
@Mapper
public interface TestDemoConvert {

    TestDemoConvert INSTANCE = Mappers.getMapper(TestDemoConvert.class);

    TestDemoDO convert(TestDemoCreateReqVO bean);

    TestDemoDO convert(TestDemoUpdateReqVO bean);

    TestDemoRespVO convert(TestDemoDO bean);

    List<TestDemoRespVO> convertList(List<TestDemoDO> list);

    PageResult<TestDemoRespVO> convertPage(PageResult<TestDemoDO> page);

    List<TestDemoExcelVO> convertList02(List<TestDemoDO> list);

}
