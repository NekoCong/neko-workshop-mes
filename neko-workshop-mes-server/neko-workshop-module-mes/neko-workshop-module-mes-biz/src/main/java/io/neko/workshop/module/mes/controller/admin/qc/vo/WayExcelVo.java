package io.neko.workshop.module.mes.controller.admin.qc.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 检验方式 Excel VO
 * @author 灵喵工坊
 * @time 2023/7/7 9:42
 */
@Data
@Accessors(chain = false)
public class WayExcelVo {

    @ExcelProperty("检验编号")
    private String  inspectCode;

    @ExcelProperty("检验名称")
    private String  inspectName;


}
