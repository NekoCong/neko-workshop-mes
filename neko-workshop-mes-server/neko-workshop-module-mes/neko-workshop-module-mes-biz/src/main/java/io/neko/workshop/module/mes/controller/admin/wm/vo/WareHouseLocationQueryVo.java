package io.neko.workshop.module.mes.controller.admin.wm.vo;

import io.neko.workshop.framework.common.pojo.PageParam;
import lombok.Data;


@Data
public class WareHouseLocationQueryVo extends PageParam {

    private String locationNumber;

    private String locationName;
}
