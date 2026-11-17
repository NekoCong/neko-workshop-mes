package io.neko.workshop.module.mes.controller.admin.md.vo;

import lombok.*;
import io.neko.workshop.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import static io.neko.workshop.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @author 灵喵工坊
 */
@Data
public class ItemQueryVo extends PageParam {


    private String itemCode;

    private String itemName;


    private String specification;


    private String unitOfMeasure;


    private String itemOrProduct;


    private Long itemTypeId;


    private String itemTypeCode;


    private String itemTypeName;

    private String vendor;


    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    private String enableFlag;

}
