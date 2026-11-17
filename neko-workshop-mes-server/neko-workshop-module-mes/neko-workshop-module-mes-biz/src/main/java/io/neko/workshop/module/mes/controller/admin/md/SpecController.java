package io.neko.workshop.module.mes.controller.admin.md;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.md.vo.NormQueryVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.PanHaoExportVo;
import io.neko.workshop.module.mes.controller.admin.md.vo.SpecExportVo;
import io.neko.workshop.module.mes.dal.dataobject.md.Spec;
import io.neko.workshop.module.mes.service.md.SpecService;
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
import static io.neko.workshop.module.mes.enums.ErrorCodeConstants.SPEC_EXIST;
import lombok.RequiredArgsConstructor;



/**
 * 规格Controller
 * @author 灵喵工坊
 */
@Tag(name = "管理后台 - 规格")
@RestController
@RequestMapping("/mes/md/spec")
@RequiredArgsConstructor
public class SpecController {


    private final SpecService specService;


    @GetMapping("/list")
    @Operation(summary = "规格列表")
    public CommonResult<PageResult<Spec>> list(NormQueryVo normQueryVo){
        return success(specService.findPage(normQueryVo));
    }


    @PostMapping("/save")
    @Operation(summary = "新增规格")
    public CommonResult<Integer> save(@RequestBody Spec spec){
        if(specService.findSpecByName(spec.getName()) != null){
            throw exception(SPEC_EXIST);
        }
        return success(specService.saveSpec(spec)).setMsg("新增成功");
    }



    @DeleteMapping("/batch")
    @Operation(summary = "删除规格")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return success(specService.removeSpecByIds(ids)).setMsg("删除成功");
    }




    @PutMapping("/update")
    @Operation(summary = "修改规格")
    public CommonResult<Integer> update(@RequestBody Spec norm){
        return success(specService.updateSpec(norm)).setMsg("修改成功");
    }




    @GetMapping("/find/{id}")
    @Operation(summary = "查询规格详情")
    public CommonResult<Spec> findNormById(@PathVariable Long id){
        return success(specService.findSpecById(id));
    }


    @GetMapping("/listAll")
    @Operation(summary = "查询规格")
    public CommonResult listAll(){
        return success(specService.listAll());
    }

    @GetMapping("/export")
    @Operation(summary = "规格导出")
    public void export(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("规格", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
            SimpleColumnWidthStyleStrategy styleStrategy = new SimpleColumnWidthStyleStrategy(20);
            EasyExcel.write(response.getOutputStream(), SpecExportVo.class).registerWriteHandler(styleStrategy).sheet("规格").doWrite(specService.listData());
        } catch (IOException e) {
            throw exception(EXPORT_DATA_ERROR);
        }
    }

}
