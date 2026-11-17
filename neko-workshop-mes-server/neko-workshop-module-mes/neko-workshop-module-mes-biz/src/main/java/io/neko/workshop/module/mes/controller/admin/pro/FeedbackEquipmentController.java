package io.neko.workshop.module.mes.controller.admin.pro;

import tools.jackson.databind.ObjectMapper;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedBackEquipmentQueryVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedBackEquipmentStatusVo;
import io.neko.workshop.module.mes.controller.admin.pro.vo.FeedBackEquipmentVo;
import io.neko.workshop.module.mes.dal.dataobject.pro.FeedbackEquipment;
import io.neko.workshop.module.mes.service.pro.FeedbackEquipmentService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;


/**
 * @author 灵喵工坊
 * @time 2023/6/20 15:33
 */
@RestController
@RequestMapping("/mes/pro/fed/equipment")
@RequiredArgsConstructor
public class FeedbackEquipmentController {


    private final FeedbackEquipmentService feedbackEquipmentService;



    @GetMapping("/list")
    public CommonResult<PageResult<FeedBackEquipmentVo>> list(FeedBackEquipmentQueryVo pageReqVO){
        List<FeedBackEquipmentVo> list =  feedbackEquipmentService.list(pageReqVO);
        PageResult<FeedBackEquipmentVo> pageResult = new PageResult<>();
        pageResult.setList(list);
        pageResult.setTotal((long) list.size());
       return  success(pageResult);
    }




    @PostMapping("/updateStatus")
    public CommonResult updateStatus(@RequestBody FeedBackEquipmentStatusVo feedBackEquipmentStatusVo) throws  Exception{
        return success( feedbackEquipmentService.updateFeedbackEquipmentStatus(feedBackEquipmentStatusVo));
    }






    @GetMapping("/get/{id}")
    public CommonResult<FeedbackEquipment> findEquipmentCodeById(@PathVariable Long id) {
        FeedbackEquipment feedbackEquipment = feedbackEquipmentService.findEquipmentCodeById(id);
        String inputJson = feedbackEquipment.getEquipmentCode();
        List<String> equipmentCodes = Arrays.asList(inputJson.split("#"));
        List<Map<String, String>> equipmentCodeList = new ArrayList<>();
        for (String code : equipmentCodes) {
            Map<String, String> equipmentCodeMap = new HashMap<>(200);
            equipmentCodeMap.put("equipmentCode", code);
            equipmentCodeList.add(equipmentCodeMap);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String equipmentCodeJson = objectMapper.writeValueAsString(equipmentCodeList);
        feedbackEquipment.setEquipmentCode(equipmentCodeJson);
        return  success(feedbackEquipment);
    }













}
