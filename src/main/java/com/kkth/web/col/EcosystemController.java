package com.kkth.web.col;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.enums.AuthTypeEnum;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.Ecosystem;
import com.kkth.web.service.EcosystemService;
import com.kkth.web.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 生态环境状况 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Slf4j
@RestController
@RequestMapping("/ecosystem")
public class EcosystemController extends SuperController {
    @Autowired
    private EcosystemService ecosystemService;

    @Autowired
    private SysResourceService sysResourceService;

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("生态环境查询")
    @GetMapping("/page")
    public ApiResponses<IPage<Ecosystem>> page(
            @RequestParam(value = "parkId", defaultValue = "") String parkId) {

        LambdaQueryChainWrapper<Ecosystem> qw = ecosystemService.lambdaQuery();
        if (StringUtils.isNotBlank(parkId)) {
            qw.like(Ecosystem::getParkId, parkId);
        }

        return success(qw.page(this.<Ecosystem>getPage()));

    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("生态环境添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody Ecosystem ecosystem) {

        ecosystemService.save(ecosystem);
        return success(HttpStatus.CREATED);
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("生态环境编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody Ecosystem ecosystem) {

        ecosystem.setId(id);
        ecosystemService.updateById(ecosystem);
        return success();
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("生态环境删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        ecosystemService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
