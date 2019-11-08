package com.kkth.web.col;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.enums.AuthTypeEnum;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.web.model.entity.Geolandscape;
import com.kkth.web.model.entity.Gueststat;
import com.kkth.web.service.GueststatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 游客统计 前端控制器
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@RestController
@RequestMapping("/gueststat")
public class GueststatController extends SuperController {

    @Autowired
    private GueststatService gueststatService;

    @Resources
    @ApiOperation("游客统计查询(分页)")
    @GetMapping("/page")
    public ApiResponses<IPage<Gueststat>> page(
            @RequestParam(value = "touristssource", defaultValue = "") String touristssource) {

        LambdaQueryChainWrapper<Gueststat> qw = gueststatService.lambdaQuery();
        if (StringUtils.isNotBlank(touristssource)) {
            qw.like(Gueststat::getTouristssource, touristssource);
        }
        return success(qw.page(this.<Gueststat>getPage()));
    }

    @Resources
    @ApiOperation("游客统计报表查询")
    @GetMapping("/list")
    public ApiResponses<List<Gueststat>> list(Integer parkId) {

        return success(gueststatService.lambdaQuery().list());
    }

    @Resources
    @ApiOperation("游客统计添加")
    @PostMapping
    public ApiResponses<Void> save(@RequestBody Gueststat gueststat) {

        gueststatService.save(gueststat);
        return success(HttpStatus.CREATED);
    }

    @Resources
    @ApiOperation("游客统计编辑")
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody Gueststat gueststat) {

        gueststat.setId(id);
        gueststatService.updateById(gueststat);
        return success();
    }

    @Resources
    @ApiOperation("游客统计删除")
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {

        gueststatService.removeById(id);
        return success(HttpStatus.NO_CONTENT);
    }
}
