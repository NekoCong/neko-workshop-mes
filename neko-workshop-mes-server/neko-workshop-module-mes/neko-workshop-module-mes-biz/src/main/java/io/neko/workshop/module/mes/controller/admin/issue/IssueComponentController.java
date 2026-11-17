package io.neko.workshop.module.mes.controller.admin.issue;

import io.neko.workshop.framework.common.exception.ErrorCode;
import io.neko.workshop.framework.common.pojo.CommonResult;
import io.neko.workshop.framework.common.pojo.PageResult;
import io.neko.workshop.module.mes.controller.admin.issue.vo.IssueComponentQueryVo;
import io.neko.workshop.module.mes.dal.dataobject.issue.IssueComponent;
import io.neko.workshop.module.mes.service.issue.IssueComponentService;
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
 * @time 2023/7/27 09:45
 */

@Tag(name = "管理后台 - 问题管理设置 - 零部件")
@RestController
@RequestMapping("/mes/issue/component")
@RequiredArgsConstructor
public class IssueComponentController {

    private final IssueComponentService issueComponentService;

    @PostMapping("/save")
    public CommonResult<Integer> save(@RequestBody @Valid IssueComponent issueComponent, BindingResult results){
        //验空
        if (results.hasErrors())
            throw  exception(new ErrorCode(1000, Objects.requireNonNull(results.getFieldError()).getDefaultMessage()));
        if (issueComponentService.findByName(issueComponent.getName()) != null) {
            throw exception(1001, "数据库中已经存在，不可以重复添加");
        }
        return success(issueComponentService.saveIssueComponent(issueComponent)).setMsg("新增成功");
    }

    @DeleteMapping("/batch")
    public CommonResult<Integer> batch(@RequestBody List<Long> ids){
        return success(issueComponentService.removeIssueComponent(ids)).setMsg("删除成功");
    }

    @PutMapping("/update")
    public CommonResult<Integer> update(@RequestBody IssueComponent issueComponent){
        return success(issueComponentService.updateIssueComponent(issueComponent)).setMsg("修改成功");
    }

    @GetMapping("/find/{id}")
    public CommonResult<IssueComponent> findIssueComponentById(@PathVariable Long id){
        return success(issueComponentService.findIssueComponentById(id));
    }

    @GetMapping("/list")
    public CommonResult<PageResult<IssueComponent>> list(IssueComponentQueryVo issueComponentQueryVo){
        return success(issueComponentService.findPage(issueComponentQueryVo));
    }

    @GetMapping("/findByName/{name}")
    public CommonResult<IssueComponent> findByName(@PathVariable String name){
        return success(issueComponentService.findByName(name));
    }

}
