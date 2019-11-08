package com.kkth.web.col;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.enums.AuthTypeEnum;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.Legal;
import com.kkth.web.service.LegalService;
import com.kkth.web.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 公园管理法律法规 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Slf4j
@RestController
@RequestMapping("/legal")
public class LegalController extends SuperController {
    @Autowired
    private LegalService legalService;

    @Autowired
    private SysResourceService sysResourceService;

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("法律法规查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<Legal>> page(
            @RequestParam(value = "filename", defaultValue = "") String filename) {

        LambdaQueryChainWrapper<Legal> qw = legalService.lambdaQuery();
        if (StringUtils.isNotBlank(filename)) {
            qw.like(Legal::getFilename, filename);
        }

        return success(qw.page(this.<Legal>getPage()));

    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("法律法规添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody Legal legal) {

        legalService.save(legal);
        return success(HttpStatus.CREATED);
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("法律法规编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody Legal legal) {

        legal.setId(id);
        legalService.updateById(legal);
        return success();
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("法律法规删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        legalService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
