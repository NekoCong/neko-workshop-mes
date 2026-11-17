package io.neko.workshop.module.mes.service.qc;

import io.neko.workshop.module.mes.controller.admin.qc.vo.ProcessRecordItemsVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ProcessRecordItem;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/11 17:39
 */
public interface ProcessRecordItemService {

    public void insertProcessFormCompare(ProcessRecordItem processRecordItem);

    public void updateProcessRecordItem(ProcessRecordItem processRecordItem);

    public int getCount(Long recordId);

    public List<ProcessRecordItem> selectCompareByNumber(String detectionOrderNumber, String status);

    public int updateProcessRecordItem(Long recordId,String itemBarCode,String flag,String detectionOrderNumber);

    public String selectMaxDetectionOrderNumber(Long recordId);

    public List<ProcessRecordItem> getProcessFormCompare(Long recordId, String detectionOrderNumber);

    public List<ProcessRecordItemsVo> getProcessFormCompares(Long recordId, String processCode,String maxSortNumber);

    public ProcessRecordItem selectRecordItemByStandardItemId(Long recordId,Long standardItemId,String sortNumber);
}
