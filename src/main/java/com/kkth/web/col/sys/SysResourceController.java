package com.kkth.web.col.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.enums.AuthTypeEnum;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.cpt.ScanMappings;
import com.kkth.web.model.entity.SysResource;
import com.kkth.web.service.SysResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@RestController
@RequestMapping("/resource")
@Validated
public class SysResourceController extends SuperController {

    @Autowired
    private SysResourceService resourceService;

    @Autowired
    private ScanMappings scanMappings;

    @Resources
    @ApiOperation("资源查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<SysResource>> page(
            @RequestParam(value = "resourceName", required = false) String resourceName,
            @RequestParam(value = "method", required = false) String method,
            @RequestParam(value = "authType", required = false) AuthTypeEnum authType) {

        return success(resourceService.lambdaQuery()
                .like(StringUtils.isNotEmpty(resourceName), SysResource::getResourceName, resourceName)
                .eq(StringUtils.isNotEmpty(method), SysResource::getMethod, method)
                .eq(Objects.nonNull(authType), SysResource::getAuthType, authType)
                .page(this.<SysResource>getPage()));
    }

    @Resources
    @ApiOperation("资源查询(所有)")
    @GetMapping("/list")
    public ApiResponses<List<SysResource>> list() {

        return success(resourceService.list());
    }

    @Resources
    @ApiOperation("资源刷新")
    @PutMapping
    public ApiResponses<Void> refresh() {

        scanMappings.doScan();
        return success();
    }

}