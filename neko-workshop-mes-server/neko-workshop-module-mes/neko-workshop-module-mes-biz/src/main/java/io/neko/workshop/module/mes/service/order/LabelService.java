package io.neko.workshop.module.mes.service.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.order.vo.DetailedInventoryResponseVO;
import io.neko.workshop.module.mes.controller.admin.order.vo.LabelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.LabelQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.order.Label;
import io.neko.workshop.module.mes.dal.dataobject.order.OutboundRecord;

import java.util.List;

/**
 * @author 灵喵工坊
 * @time 2023/7/27 15:32
 */
public interface LabelService {


    Integer saveLabel(Label label);


    Integer removeLabelByIds(List<Long> ids);


    Integer updateLabel(Label label);


    Label findLabelById(Long id);


    PageResult<Label> findPage(LabelQueryVo labelVo);


    Integer updateStatus(Long id,String status);

    List<Label> findLabelByStatus(String status);

    List<Label> listAll();

    List<LabelExportVo> listData();

    Label findByBarCode(String barCode);

    List<Label> findByPanHao(String panhao);

    PageResult<Label> findRepackagedListPage(LabelQueryVo labelVo);

    OutboundRecord countRepackage();

    List<Label> findByReelNumber(String panhao);

    List<DetailedInventoryResponseVO> detailedInventory(String lineType, String model, String spec);

}
