package io.neko.workshop.module.system.api.dict;

import io.neko.workshop.module.system.api.dict.dto.DictDataRespDTO;
import io.neko.workshop.module.system.convert.dict.DictDataConvert;
import io.neko.workshop.module.system.dal.dataobject.dict.DictDataDO;
import io.neko.workshop.module.system.service.dict.DictDataService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

/**
 * 字典数据 API 实现类
 *
 * @author 灵喵工坊
 */
@Service
@RequiredArgsConstructor
public class DictDataApiImpl implements DictDataApi {

    private final DictDataService dictDataService;

    @Override
    public void validateDictDataList(String dictType, Collection<String> values) {
        dictDataService.validateDictDataList(dictType, values);
    }

    @Override
    public DictDataRespDTO getDictData(String dictType, String value) {
        DictDataDO dictData = dictDataService.getDictData(dictType, value);
        return DictDataConvert.INSTANCE.convert02(dictData);
    }

    @Override
    public DictDataRespDTO parseDictData(String dictType, String label) {
        DictDataDO dictData = dictDataService.parseDictData(dictType, label);
        return DictDataConvert.INSTANCE.convert02(dictData);
    }

}
