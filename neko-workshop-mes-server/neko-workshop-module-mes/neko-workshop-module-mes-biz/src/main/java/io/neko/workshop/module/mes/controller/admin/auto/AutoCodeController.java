package io.neko.workshop.module.mes.controller.admin.auto;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.module.mes.utils.AutoCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author 灵喵工坊
 */
@RestController
@RequestMapping("/system/autocode")
@RequiredArgsConstructor
public class AutoCodeController {


    private final AutoCodeUtil autoCodeUtil;


    @GetMapping(value = {"/get/{ruleCode}/{inputCharacter}","/get/{ruleCode}"})
    public CommonResult getAutoCode(@PathVariable String ruleCode, @PathVariable(required = false) String inputCharacter){
        return CommonResult.success(autoCodeUtil.genSerialCode(ruleCode, inputCharacter));
    }


}
