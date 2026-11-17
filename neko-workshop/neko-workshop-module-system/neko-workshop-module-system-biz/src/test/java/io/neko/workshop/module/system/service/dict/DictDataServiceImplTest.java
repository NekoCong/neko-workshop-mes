package io.neko.workshop.module.system.service.dict;

import io.neko.workshop.framework.common.enums.CommonStatusEnum;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.common.util.collection.ArrayUtils;
import io.neko.workshop.framework.test.core.ut.BaseDbUnitTest;
import io.neko.workshop.module.system.controller.admin.dict.vo.data.DictDataCreateReqVO;
import io.neko.workshop.module.system.controller.admin.dict.vo.data.DictDataExportReqVO;
import io.neko.workshop.module.system.controller.admin.dict.vo.data.DictDataPageReqVO;
import io.neko.workshop.module.system.controller.admin.dict.vo.data.DictDataUpdateReqVO;
import io.neko.workshop.module.system.dal.dataobject.dict.DictDataDO;
import io.neko.workshop.module.system.dal.dataobject.dict.DictTypeDO;
import io.neko.workshop.module.system.dal.mysql.dict.DictDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.Import;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import static io.neko.workshop.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static io.neko.workshop.framework.test.core.util.AssertUtils.assertPojoEquals;
import static io.neko.workshop.framework.test.core.util.AssertUtils.assertServiceException;
import static io.neko.workshop.framework.test.core.util.RandomUtils.*;
import static io.neko.workshop.module.system.enums.ErrorCodeConstants.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Import(DictDataServiceImpl.class)
public class DictDataServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DictDataServiceImpl dictDataService;

    @Resource
    private DictDataMapper dictDataMapper;
    @MockitoBean
    private DictTypeService dictTypeService;

    @Test
    public void testGetDictDataList() {
        // mock 数据
        DictDataDO dictDataDO01 = randomDictDataDO().setDictType("neko-workshop").setSort(2);
        dictDataMapper.insert(dictDataDO01);
        DictDataDO dictDataDO02 = randomDictDataDO().setDictType("neko-workshop").setSort(1);
        dictDataMapper.insert(dictDataDO02);
        // 准备参数

        // 调用
        List<DictDataDO> dictDataDOList = dictDataService.getDictDataList();
        // 断言
        assertEquals(2, dictDataDOList.size());
        assertPojoEquals(dictDataDO02, dictDataDOList.get(0));
        assertPojoEquals(dictDataDO01, dictDataDOList.get(1));
    }

    @Test
    public void testGetDictDataPage() {
        // mock 数据
        DictDataDO dbDictData = randomPojo(DictDataDO.class, o -> { // 等会查询到
            o.setLabel("灵喵工坊");
            o.setDictType("neko-workshop");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
        });
        dictDataMapper.insert(dbDictData);
        // 测试 label 不匹配
        dictDataMapper.insert(cloneIgnoreId(dbDictData, o -> o.setLabel("士")));
        // 测试 dictType 不匹配
        dictDataMapper.insert(cloneIgnoreId(dbDictData, o -> o.setDictType("tudou")));
        // 测试 status 不匹配
        dictDataMapper.insert(cloneIgnoreId(dbDictData, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 准备参数
        DictDataPageReqVO reqVO = new DictDataPageReqVO();
        reqVO.setLabel("灵喵");
        reqVO.setDictType("neko-workshop");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());

        // 调用
        PageResult<DictDataDO> pageResult = dictDataService.getDictDataPage(reqVO);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(dbDictData, pageResult.getList().get(0));
    }

    @Test
    public void testGetDictDataList_export() {
        // mock 数据
        DictDataDO dbDictData = randomPojo(DictDataDO.class, o -> { // 等会查询到
            o.setLabel("灵喵工坊");
            o.setDictType("neko-workshop");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
        });
        dictDataMapper.insert(dbDictData);
        // 测试 label 不匹配
        dictDataMapper.insert(cloneIgnoreId(dbDictData, o -> o.setLabel("士")));
        // 测试 dictType 不匹配
        dictDataMapper.insert(cloneIgnoreId(dbDictData, o -> o.setDictType("tudou")));
        // 测试 status 不匹配
        dictDataMapper.insert(cloneIgnoreId(dbDictData, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 准备参数
        DictDataExportReqVO reqVO = new DictDataExportReqVO();
        reqVO.setLabel("灵喵");
        reqVO.setDictType("neko-workshop");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());

        // 调用
        List<DictDataDO> list = dictDataService.getDictDataList(reqVO);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(dbDictData, list.get(0));
    }

    @Test
    public void testGetDictData() {
        // mock 数据
        DictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);
        // 准备参数
        Long id = dbDictData.getId();

        // 调用
        DictDataDO dictData = dictDataService.getDictData(id);
        // 断言
        assertPojoEquals(dbDictData, dictData);
    }

    @Test
    public void testCreateDictData_success() {
        // 准备参数
        DictDataCreateReqVO reqVO = randomPojo(DictDataCreateReqVO.class,
                o -> o.setStatus(randomCommonStatus()));
        // mock 方法
        when(dictTypeService.getDictType(eq(reqVO.getDictType()))).thenReturn(randomDictTypeDO(reqVO.getDictType()));

        // 调用
        Long dictDataId = dictDataService.createDictData(reqVO);
        // 断言
        assertNotNull(dictDataId);
        // 校验记录的属性是否正确
        DictDataDO dictData = dictDataMapper.selectById(dictDataId);
        assertPojoEquals(reqVO, dictData);
    }

    @Test
    public void testUpdateDictData_success() {
        // mock 数据
        DictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DictDataUpdateReqVO reqVO = randomPojo(DictDataUpdateReqVO.class, o -> {
            o.setId(dbDictData.getId()); // 设置更新的 ID
            o.setStatus(randomCommonStatus());
        });
        // mock 方法，字典类型
        when(dictTypeService.getDictType(eq(reqVO.getDictType()))).thenReturn(randomDictTypeDO(reqVO.getDictType()));

        // 调用
        dictDataService.updateDictData(reqVO);
        // 校验是否更新正确
        DictDataDO dictData = dictDataMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dictData);
    }

    @Test
    public void testDeleteDictData_success() {
        // mock 数据
        DictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDictData.getId();

        // 调用
        dictDataService.deleteDictData(id);
        // 校验数据不存在了
        assertNull(dictDataMapper.selectById(id));
    }

    @Test
    public void testValidateDictDataExists_success() {
        // mock 数据
        DictDataDO dbDictData = randomDictDataDO();
        dictDataMapper.insert(dbDictData);// @Sql: 先插入出一条存在的数据

        // 调用成功
        dictDataService.validateDictDataExists(dbDictData.getId());
    }

    @Test
    public void testValidateDictDataExists_notExists() {
        assertServiceException(() -> dictDataService.validateDictDataExists(randomLongId()), DICT_DATA_NOT_EXISTS);
    }

    @Test
    public void testValidateDictTypeExists_success() {
        // mock 方法，数据类型被禁用
        String type = randomString();
        when(dictTypeService.getDictType(eq(type))).thenReturn(randomDictTypeDO(type));

        // 调用, 成功
        dictDataService.validateDictTypeExists(type);
    }

    @Test
    public void testValidateDictTypeExists_notExists() {
        assertServiceException(() -> dictDataService.validateDictTypeExists(randomString()), DICT_TYPE_NOT_EXISTS);
    }

    @Test
    public void testValidateDictTypeExists_notEnable() {
        // mock 方法，数据类型被禁用
        String dictType = randomString();
        when(dictTypeService.getDictType(eq(dictType))).thenReturn(
                randomPojo(DictTypeDO.class, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));

        // 调用, 并断言异常
        assertServiceException(() -> dictDataService.validateDictTypeExists(dictType), DICT_TYPE_NOT_ENABLE);
    }

    @Test
    public void testValidateDictDataValueUnique_success() {
        // 调用，成功
        dictDataService.validateDictDataValueUnique(randomLongId(), randomString(), randomString());
    }

    @Test
    public void testValidateDictDataValueUnique_valueDuplicateForCreate() {
        // 准备参数
        String dictType = randomString();
        String value = randomString();
        // mock 数据
        dictDataMapper.insert(randomDictDataDO(o -> {
            o.setDictType(dictType);
            o.setValue(value);
        }));

        // 调用，校验异常
        assertServiceException(() -> dictDataService.validateDictDataValueUnique(null, dictType, value),
                DICT_DATA_VALUE_DUPLICATE);
    }

    @Test
    public void testValidateDictDataValueUnique_valueDuplicateForUpdate() {
        // 准备参数
        Long id = randomLongId();
        String dictType = randomString();
        String value = randomString();
        // mock 数据
        dictDataMapper.insert(randomDictDataDO(o -> {
            o.setDictType(dictType);
            o.setValue(value);
        }));

        // 调用，校验异常
        assertServiceException(() -> dictDataService.validateDictDataValueUnique(id, dictType, value),
                DICT_DATA_VALUE_DUPLICATE);
    }

    @Test
    public void testCountByDictType() {
        // mock 数据
        dictDataMapper.insert(randomDictDataDO(o -> o.setDictType("neko-workshop")));
        dictDataMapper.insert(randomDictDataDO(o -> o.setDictType("tudou")));
        dictDataMapper.insert(randomDictDataDO(o -> o.setDictType("neko-workshop")));
        // 准备参数
        String dictType = "neko-workshop";

        // 调用
        long count = dictDataService.countByDictType(dictType);
        // 校验
        assertEquals(2L, count);
    }

    @Test
    public void testValidateDictDataList_success() {
        // mock 数据
        DictDataDO dictDataDO = randomDictDataDO().setStatus(CommonStatusEnum.ENABLE.getStatus());
        dictDataMapper.insert(dictDataDO);
        // 准备参数
        String dictType = dictDataDO.getDictType();
        List<String> values = singletonList(dictDataDO.getValue());

        // 调用，无需断言
        dictDataService.validateDictDataList(dictType, values);
    }

    @Test
    public void testValidateDictDataList_notFound() {
        // 准备参数
        String dictType = randomString();
        List<String> values = singletonList(randomString());

        // 调用, 并断言异常
        assertServiceException(() -> dictDataService.validateDictDataList(dictType, values), DICT_DATA_NOT_EXISTS);
    }

    @Test
    public void testValidateDictDataList_notEnable() {
        // mock 数据
        DictDataDO dictDataDO = randomDictDataDO().setStatus(CommonStatusEnum.DISABLE.getStatus());
        dictDataMapper.insert(dictDataDO);
        // 准备参数
        String dictType = dictDataDO.getDictType();
        List<String> values = singletonList(dictDataDO.getValue());

        // 调用, 并断言异常
        assertServiceException(() -> dictDataService.validateDictDataList(dictType, values),
                DICT_DATA_NOT_ENABLE, dictDataDO.getLabel());
    }

    @Test
    public void testGetDictData_dictType() {
        // mock 数据
        DictDataDO dictDataDO = randomDictDataDO().setDictType("neko-workshop").setValue("1");
        dictDataMapper.insert(dictDataDO);
        DictDataDO dictDataDO02 = randomDictDataDO().setDictType("neko-workshop").setValue("2");
        dictDataMapper.insert(dictDataDO02);
        // 准备参数
        String dictType = "neko-workshop";
        String value = "1";

        // 调用
        DictDataDO dbDictData = dictDataService.getDictData(dictType, value);
        // 断言（用 assertPojoEquals，避免 H2 微秒精度与纳秒时间戳的差异导致误报）
        assertPojoEquals(dictDataDO, dbDictData);
    }

    @Test
    public void testParseDictData() {
        // mock 数据
        DictDataDO dictDataDO = randomDictDataDO().setDictType("neko-workshop").setLabel("1");
        dictDataMapper.insert(dictDataDO);
        DictDataDO dictDataDO02 = randomDictDataDO().setDictType("neko-workshop").setLabel("2");
        dictDataMapper.insert(dictDataDO02);
        // 准备参数
        String dictType = "neko-workshop";
        String label = "1";

        // 调用
        DictDataDO dbDictData = dictDataService.parseDictData(dictType, label);
        // 断言（用 assertPojoEquals，避免 H2 微秒精度与纳秒时间戳的差异导致误报）
        assertPojoEquals(dictDataDO, dbDictData);
    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static DictDataDO randomDictDataDO(Consumer<DictDataDO>... consumers) {
        Consumer<DictDataDO> consumer = (o) -> {
            o.setStatus(randomCommonStatus()); // 保证 status 的范围
        };
        return randomPojo(DictDataDO.class, ArrayUtils.append(consumer, consumers));
    }

    /**
     * 生成一个有效的字典类型
     *
     * @param type 字典类型
     * @return DictTypeDO 对象
     */
    private static DictTypeDO randomDictTypeDO(String type) {
        return randomPojo(DictTypeDO.class, o -> {
            o.setType(type);
            o.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 保证 status 是开启
        });
    }

}
