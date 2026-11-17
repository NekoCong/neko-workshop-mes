package io.neko.workshop.module.infra.service.test;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoCreateReqVO;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoExportReqVO;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoPageReqVO;
import io.neko.workshop.module.infra.controller.admin.test.vo.TestDemoUpdateReqVO;
import io.neko.workshop.module.infra.convert.test.TestDemoConvert;
import io.neko.workshop.module.infra.dal.dataobject.test.TestDemoDO;
import io.neko.workshop.module.infra.dal.mysql.test.TestDemoMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.module.infra.enums.ErrorCodeConstants.TEST_DEMO_NOT_EXISTS;
import lombok.RequiredArgsConstructor;

/**
 * 字典类型 Service 实现类
 *
 * @author 灵喵工坊
 */
@Service
@Validated
@RequiredArgsConstructor
public class TestDemoServiceImpl implements TestDemoService {

    private final TestDemoMapper testDemoMapper;

    @Override
    public Long createTestDemo(TestDemoCreateReqVO createReqVO) {
        // 插入
        TestDemoDO testDemo = TestDemoConvert.INSTANCE.convert(createReqVO);
        testDemoMapper.insert(testDemo);
        // 返回
        return testDemo.getId();
    }

    @Override
    @CacheEvict(value = "test", key = "#updateReqVO.id")
    public void updateTestDemo(TestDemoUpdateReqVO updateReqVO) {
        // 校验存在
        validateTestDemoExists(updateReqVO.getId());
        // 更新
        TestDemoDO updateObj = TestDemoConvert.INSTANCE.convert(updateReqVO);
        testDemoMapper.updateById(updateObj);
    }

    @Override
    @CacheEvict(value = "test", key = "#id")
    public void deleteTestDemo(Long id) {
        // 校验存在
        validateTestDemoExists(id);
        // 删除
        testDemoMapper.deleteById(id);
    }

    private void validateTestDemoExists(Long id) {
        if (testDemoMapper.selectById(id) == null) {
            throw exception(TEST_DEMO_NOT_EXISTS);
        }
    }

    @Override
    @Cacheable(cacheNames = "test", key = "#id")
    public TestDemoDO getTestDemo(Long id) {
        return testDemoMapper.selectById(id);
    }

    @Override
    public List<TestDemoDO> getTestDemoList(Collection<Long> ids) {
        return testDemoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TestDemoDO> getTestDemoPage(TestDemoPageReqVO pageReqVO) {
        testDemoMapper.selectList2();
        return testDemoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TestDemoDO> getTestDemoList(TestDemoExportReqVO exportReqVO) {
        return testDemoMapper.selectList(exportReqVO);
    }

}
