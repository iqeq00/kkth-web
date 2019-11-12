package com.kkth.web.col;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.LevelEvolution;
import com.kkth.web.service.LevelEvolutionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 级别沿革 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/levelEvolution")
public class LevelEvolutionController extends SuperController {

    @Autowired
    private LevelEvolutionService levelEvolutionService;

    @Resources
    @ApiOperation("级别沿革查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<LevelEvolution>> page(
            @RequestParam(value = "name", defaultValue = "") String name) {

        LambdaQueryChainWrapper<LevelEvolution> qw = levelEvolutionService.lambdaQuery();
        if (StringUtils.isNotBlank(name)) {
            qw.like(LevelEvolution::getName, name);
        }
        return success(qw.page(this.<LevelEvolution>getPage()));
    }

    @Resources
    @ApiOperation("级别沿革添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody LevelEvolution levelEvolution) {

        levelEvolutionService.save(levelEvolution);
        return success(HttpStatus.CREATED);
    }

    @Resources
    @ApiOperation("级别沿革编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody LevelEvolution levelEvolution) {

        levelEvolution.setId(id);
        levelEvolutionService.updateById(levelEvolution);
        return success();
    }

    @Resources
    @ApiOperation("级别沿革删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        levelEvolutionService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
