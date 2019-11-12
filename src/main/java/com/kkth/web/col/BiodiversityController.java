package com.kkth.web.col;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.Biodiversity;
import com.kkth.web.service.BiodiversityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 生物多样性 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/biodiversity")
public class BiodiversityController extends SuperController {

    @Autowired
    private BiodiversityService biodiversityService;

    @Resources
    @ApiOperation("生物多样性查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<Biodiversity>> page(
            @RequestParam(value = "chineseName", defaultValue = "") String chineseName) {

        LambdaQueryChainWrapper<Biodiversity> qw = biodiversityService.lambdaQuery();
        if (StringUtils.isNotBlank(chineseName)) {
            qw.like(Biodiversity::getChineseName, chineseName);
        }
        return success(qw.page(this.<Biodiversity>getPage()));
    }

    @Resources
    @ApiOperation("生物多样性添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody Biodiversity biodiversity) {

        biodiversityService.save(biodiversity);
        return success(HttpStatus.CREATED);
    }

    @Resources
    @ApiOperation("生物多样性编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody Biodiversity biodiversity) {

        biodiversity.setId(id);
        biodiversityService.updateById(biodiversity);
        return success();
    }

    @Resources
    @ApiOperation("生物多样性删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        biodiversityService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
