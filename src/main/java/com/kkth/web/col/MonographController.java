package com.kkth.web.col;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.Monograph;
import com.kkth.web.service.MonographService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 专著 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/monograph")
public class MonographController extends SuperController {

    @Autowired
    private MonographService monographService;

    @Resources
    @ApiOperation("专著查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<Monograph>> page(
            @RequestParam(value = "name", defaultValue = "") String name) {

        LambdaQueryChainWrapper<Monograph> qw = monographService.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            qw.like(Monograph::getName, name);
        }
        return success(qw.page(this.<Monograph>getPage()));
    }

    @Resources
    @ApiOperation("专著添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody Monograph monograph) {

        monographService.save(monograph);
        return success(HttpStatus.CREATED);
    }

    @Resources
    @ApiOperation("专著编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody Monograph monograph) {

        monograph.setId(id);
        monographService.updateById(monograph);
        return success();
    }

    @Resources
    @ApiOperation("专著删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        monographService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
