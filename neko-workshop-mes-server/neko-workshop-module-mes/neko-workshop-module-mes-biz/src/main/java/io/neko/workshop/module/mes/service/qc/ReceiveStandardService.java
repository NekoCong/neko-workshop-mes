package io.neko.workshop.module.mes.service.qc;


import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.qc.vo.ReceiveStandardQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.qc.ReceiveStandard;
import io.neko.workshop.module.mes.dal.dataobject.qc.Standard;


import java.util.List;


/**
 * @author 灵喵工坊
 * @time 2023/7/26 11:01
 */
public interface ReceiveStandardService {

    PageResult<ReceiveStandard> findPage(ReceiveStandardQueryVo receiveStandardQueryVo);

    List<ReceiveStandard> findReceiveStandardList();

    List<ReceiveStandard> findReceiveStandardByItemCode(String itemCode);

    Integer saveReceiveStandard(ReceiveStandard receiveStandard);

    ReceiveStandard findReceiveStandardById(Long id);

    Integer updateReceiveStandard(ReceiveStandard receiveStandard);

    Integer removeReceiveStandard(List<Long> ids);

    ReceiveStandard findReceiveStandardByItemCode(String itemCode,String enableFlag);

}
