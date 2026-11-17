package io.neko.workshop.module.mes.controller.admin.md;

import io.neko.workshop.module.mes.dal.dataobject.md.UnitMeasure;
import io.neko.workshop.module.mes.service.md.UnitMeasureService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.*;

import io.neko.workshop.framework.common.pojo.CommonResult;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.*;



/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@Tag(name = "管理后台 - 单位换算")
@RestController
@RequestMapping("/mes/md/unit/conversion")
public class UnitConversionController {





}
