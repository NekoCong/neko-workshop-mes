package io.neko.workshop.module.mes.service.data;

import io.neko.workshop.module.mes.dal.dataobject.data.CustomDictData;
import java.util.List;

/**
 * 数据字典 Service 接口
 * @author 灵喵工坊
 */
public interface CustomDictDataService {


    /**
     * 暂停原因数据字典列表
     *
     * @return List<CustomDictData>
     */
    List<CustomDictData> findListData();

    /**
     * 线别数据字典列表
     *
     * @return List<CustomDictData>
     */
    List<CustomDictData> listLineType();


    /**
     * 品类数据字典列表
     *
     * @return List<CustomDictData>
     */
    List<CustomDictData> listCategory();

    /**
     * 类别数据字典列表
     *
     * @return List<CustomDictData>
     */
    List<CustomDictData> listKind();


    CustomDictData findOutBoundLimit();
}
