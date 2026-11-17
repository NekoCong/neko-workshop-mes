package io.neko.workshop.module.mes.service.md;

import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.md.vo.NormQueryVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.SpecExportVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Spec;

import java.util.List;


/**
 * 规格Service
 * @author 灵喵工坊
 */
public interface SpecService {

    /**
     * 新增规格
     * @param spec
     * @return Integer
     */
    Integer saveSpec(Spec spec);

    /**
     *  批量删除规格
     * @param ids
     * @return Integer
     */
    Integer removeSpecByIds(List<Long> ids);

    /**
     * 修改规格
     * @param spec
     * @return Integer
     */
    Integer updateSpec(Spec spec);

    /**
     * 根据id查询详情
     * @param id
     * @return Spec
     */
    Spec findSpecById(Long id);

    /**
     * 规格条件分页查询
     * @param normQueryVo
     * @return PageResult<Spec>
     */
    PageResult<Spec> findPage(NormQueryVo normQueryVo);

    /**
     * 根据名称查询规格信息
     * @param name
     * @return Spec
     */
    Spec findSpecByName(String name);


    List<Spec> listAll();

    List<SpecExportVo> listData();
}
