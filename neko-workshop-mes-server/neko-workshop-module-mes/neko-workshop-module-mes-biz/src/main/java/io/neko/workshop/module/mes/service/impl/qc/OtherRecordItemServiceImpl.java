package io.neko.workshop.module.mes.service.impl.qc;

import io.neko.workshop.module.mes.dal.dataobject.qc.OtherRecordItem;
import io.neko.workshop.module.mes.dal.mysql.qc.OtherRecordItemMapper;
import io.neko.workshop.module.mes.service.qc.OtherRecordItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/8/19 10:28
 */
@Service
@RequiredArgsConstructor
public class OtherRecordItemServiceImpl implements OtherRecordItemService {

    private final OtherRecordItemMapper otherRecordItemMapper;
    @Override
    public Integer saveOtherRecordItem(OtherRecordItem otherRecordItem) {
        return otherRecordItemMapper.insert(otherRecordItem);
    }

    @Override
    public List<OtherRecordItem> findOtherRecordItem(String recordNumber) {
        return otherRecordItemMapper.findOtherRecordItem(recordNumber);
    }
}
