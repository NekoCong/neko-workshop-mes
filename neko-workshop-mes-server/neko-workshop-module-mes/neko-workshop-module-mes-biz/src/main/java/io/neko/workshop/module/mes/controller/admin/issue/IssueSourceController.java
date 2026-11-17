package io.neko.workshop.module.mes.controller.admin.issue;

import io.neko.workshop.framework.common.exception.ErrorCode;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueSourceQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueSource;
import io.neko.workshop.module.mes.service.issue.IssueSourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;

import static io.neko.workshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static io.neko.workshop.framework.common.pojo.CommonResult.success;
import lombok.RequiredArgsConstructor;

/**
 * io.neko.workshop.module.mes.controller.admin.issue
 *
 * @author 灵喵工坊
 * @time 2023/7/27 16:06
 */


@Tag(name = "管理后台 - 问题管理设置 - 问题来源")
@RestController
@RequestMapping("/mes/issue/source")
@RequiredArgsConstructor
public class IssueSourceController {

    private final IssueSourceService issueSourceService;

    @PostMapping("/save")
    public CommonResult<Integer> save(@RequestBody @Valid IssueSource issueSource, BindingResult results){
        //验空
        if (results.hasErrors())
            throw  exception(new ErrorCode(1000, Objects.requireNonNull(results.getFieldError()).getDefaultMessage()));
        if (!issueSourceService.findBySourceName(issueSource.getName()).isEmpty()) {
            throw exception(1001, "数据库中已经存在，不可以重复添加");
        }
        return success(issueSourceService.saveIssueSource(issueSource)).setMsg("新增成功");
    }

    @DeleteMapping("/batch")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return success(issueSourceService.removeIssueSource(ids)).setMsg("删除成功");
    }

    @PutMapping("/update")
    public CommonResult<Integer> update(@RequestBody IssueSource issueSource){
        return success(issueSourceService.updateIssueSource(issueSource)).setMsg("修改成功");
    }

    @GetMapping("/find/{id}")
    public CommonResult<IssueSource> findIssueSourceById(@PathVariable Long id){
        return success(issueSourceService.findIssueSourceById(id));
    }

    @GetMapping("/list")
    public CommonResult<PageResult<IssueSource>> list(IssueSourceQueryVo issueSourceQueryVo){
        return success(issueSourceService.findPage(issueSourceQueryVo));
    }

    @GetMapping("/findByName/{name}")
    public CommonResult<IssueSource> findByName(@PathVariable String name){
        return success(issueSourceService.findByName(name));
    }

}
