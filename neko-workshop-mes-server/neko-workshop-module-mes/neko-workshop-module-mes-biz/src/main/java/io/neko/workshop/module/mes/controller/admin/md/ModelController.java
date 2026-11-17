package io.neko.workshop.module.mes.controller.admin.md;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.md.vo.ModelExportVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.ModelQueryVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.PanHaoExportVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Model;
import io.neko.workshop.module.mes.service.md.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.EXPORT_DATA_ERROR;
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.MODEL_EXIST;
import lombok.RequiredArgsConstructor;


/**
 * 型号Controller
 * @author 灵喵工坊
 */
@Tag(name = "管理后台 - 型号")
@RestController
@RequestMapping("/mes/md/model")
@RequiredArgsConstructor
public class ModelController {


    private final ModelService modelService;



    @GetMapping("/list")
    @Operation(summary = "型号列表")
    public CommonResult<PageResult<Model>> list(ModelQueryVo modelQueryVo){
        return success(modelService.findPage(modelQueryVo));
    }

    @PostMapping("/save")
    @Operation(summary = "新增型号")
    public CommonResult<Integer> save(@RequestBody Model model){
        if(modelService.findModelByName(model.getName()) != null){
            throw exception(MODEL_EXIST);
        }
        return success(modelService.saveModel(model)).setMsg("新增成功");
    }



    @DeleteMapping("/batch")
    @Operation(summary = "删除型号")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return success(modelService.removeModelByIds(ids)).setMsg("删除成功");
    }



    @PutMapping("/update")
    @Operation(summary = "修改型号")
    public CommonResult<Integer> update(@RequestBody Model model){
        return success(modelService.updateModel(model)).setMsg("修改成功");
    }



    @GetMapping("/find/{id}")
    @Operation(summary = "型号详情")
    public CommonResult<Model> findModelById(@PathVariable Long id){
        return success(modelService.findModelById(id));
    }


    @GetMapping("/listAll")
    @Operation(summary = "查询所有型号")
    public CommonResult listAll(){
        return success(modelService.listAll());
    }

    @GetMapping("/export")
    @Operation(summary = "型号导出")
    public void export(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("型号", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
            SimpleColumnWidthStyleStrategy styleStrategy = new SimpleColumnWidthStyleStrategy(20);
            EasyExcel.write(response.getOutputStream(), ModelExportVo.class).registerWriteHandler(styleStrategy).sheet("型号").doWrite(modelService.listData());
        } catch (IOException e) {
            throw exception(EXPORT_DATA_ERROR);
        }
    }
}
