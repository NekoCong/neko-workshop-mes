package io.neko.workshop.module.mes.service.impl.order;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.dv.vo.MachineryExcelVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelExportVo;
import io.neko.workshop.module.mes.controller.admin.order.vo.QuoteModelQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.dv.Machinery;
import io.neko.workshop.module.mes.dal.dataobject.order.QuoteModel;
import io.neko.workshop.module.mes.dal.mysql.order.QuoteModelMapper;
import io.neko.workshop.module.mes.service.order.QuoteModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 * @time 2023/7/17 15:05
 */
@Service
@RequiredArgsConstructor
public class QuoteModelServiceImpl implements QuoteModelService {


    private final QuoteModelMapper quoteModelMapper;



    @Override
    public Integer saveOrderQuoteModel(QuoteModel orderQuoteModel) {
        orderQuoteModel.setCreateTime(new Date());
        return quoteModelMapper.insert(orderQuoteModel);
    }



    @Override
    public Integer removeOrderQuoteModel(List<Long> ids) {
        return quoteModelMapper.deleteBatchIds(ids);
    }



    @Override
    public Integer updateOrderQuoteModel(QuoteModel orderQuoteModel) {
        orderQuoteModel.setUpdateTime(new Date());
        return quoteModelMapper.updateById(orderQuoteModel);
    }



    @Override
    public QuoteModel findOrderQuoteModelById(Long id) {
        return quoteModelMapper.selectById(id);
    }



    @Override
    public PageResult<QuoteModel> findPage(QuoteModelQueryVo orderQuoteModelQueryVo) {
        return quoteModelMapper.findPage(orderQuoteModelQueryVo);
    }

    @Override
    public QuoteModel findQuoteModelByName(String model) {
        return quoteModelMapper.selectOne(new LambdaQueryWrapperX<QuoteModel>().eq(QuoteModel::getModel,model));
    }

    @Override
    public List<QuoteModel> selectQuoteModel() {
        return quoteModelMapper.selectList();
    }

    @Override
    public List<QuoteModelExportVo> exportData() {
        List<QuoteModel> quoteModelList = quoteModelMapper.selectList();
        ArrayList<QuoteModelExportVo> quoteModelExportVo = new ArrayList<>(quoteModelList.size());
        quoteModelList.forEach(dict -> {
            QuoteModelExportVo excelDictDTO = new QuoteModelExportVo();
            BeanUtils.copyProperties(dict, excelDictDTO);
            quoteModelExportVo.add(excelDictDTO);
        });
        return quoteModelExportVo;
    }

}
