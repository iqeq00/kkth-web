package com.kkth.web.col;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.MajorProtection;
import com.kkth.web.service.MajorProtectionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 重点保护对象 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/majorProtection")
public class MajorProtectionController extends SuperController {

    @Autowired
    private MajorProtectionService majorProtectionService;

    @Resources
    @ApiOperation("重点保护对象查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<MajorProtection>> page(
            @RequestParam(value = "name", defaultValue = "") String name) {

        LambdaQueryChainWrapper<MajorProtection> qw = majorProtectionService.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            qw.like(MajorProtection::getName, name);
        }
        return success(qw.page(this.<MajorProtection>getPage()));
    }

    @Resources
    @ApiOperation("重点保护对象添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody MajorProtection majorProtection) {

        majorProtectionService.save(majorProtection);
        return success(HttpStatus.CREATED);
    }

    @Resources
    @ApiOperation("重点保护对象编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody MajorProtection majorProtection) {

        majorProtection.setId(id);
        majorProtectionService.updateById(majorProtection);
        return success();
    }

    @Resources
    @ApiOperation("重点保护对象删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        majorProtectionService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
