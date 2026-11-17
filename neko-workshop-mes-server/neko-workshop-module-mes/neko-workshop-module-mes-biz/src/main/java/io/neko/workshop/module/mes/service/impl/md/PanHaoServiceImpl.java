package io.neko.workshop.module.mes.service.impl.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.framework.mybatis.core.query.LambdaQueryWrapperX;
import io.neko.workshop.module.mes.controller.admin.md.vo.PanHaoExportVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.PanHaoQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.md.PanHao;
import io.neko.workshop.module.mes.dal.mysql.md.PanHaoMapper;
import io.neko.workshop.module.mes.service.md.PanHaoService;
import io.neko.workshop.module.mes.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 盘号ServiceImpl
 *
 * @author 灵喵工坊MES
 */
@Service
@RequiredArgsConstructor
public class PanHaoServiceImpl implements PanHaoService {

    private final PanHaoMapper panHaoMapper;


    @Override
    public Integer savePanHao(PanHao panHao) {
        return panHaoMapper.insert(panHao);
    }

    @Override
    public Integer removePanHaoByIds(List<Long> ids) {
        return panHaoMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updatePanHao(PanHao panHao) {
        return panHaoMapper.updateById(panHao);
    }


    @Override
    public PageResult<PanHao> findPage(PanHaoQueryVo panHaoQueryVo) {
        return panHaoMapper.findPage(panHaoQueryVo);
    }

    @Override
    public PanHao findPanHaoByNum(String number) {
        return panHaoMapper.selectOne(new LambdaQueryWrapperX<PanHao>().eq(PanHao::getNumber,number));
    }

    @Override
    public List<PanHao> listAll() {
        return panHaoMapper.selectList();
    }

    @Override
    public List<PanHaoExportVo> listData() {
        return BeanCopyUtil.copyListProperties(this.listAll(), PanHaoExportVo::new);
    }

}
