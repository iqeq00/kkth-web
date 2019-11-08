package com.kkth.web.col.sys;

import com.kkth.framework.annotations.ApiOperation;
import com.kkth.framework.annotations.Resources;
import com.kkth.framework.controller.SuperController;
import com.kkth.framework.enums.AuthTypeEnum;
import com.kkth.framework.responses.ApiResponses;
import com.kkth.framework.utils.IpUtils;
import com.kkth.web.model.entity.SysUser;
import com.kkth.web.model.param.LoginPARM;
import com.kkth.web.model.param.PasswordPARM;
import com.kkth.web.model.vo.MenuTreeVo;
import com.kkth.web.model.vo.TokenVo;
import com.kkth.web.model.vo.UserDetailsVo;
import com.kkth.web.service.SysMenuService;
import com.kkth.web.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 账户 前端控制器
 * </p>
 *
 * @author lichee
 */
@RestController
@RequestMapping("/account")
@Validated
public class AccountController extends SuperController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysMenuService menuService;

    @Resources(AuthTypeEnum.OPEN)
    @ApiOperation("账户获取")
    @PostMapping("/token")
    public ApiResponses<TokenVo> getToken(@RequestBody @Validated LoginPARM loginPARM) {

        SysUser user = userService.login(loginPARM.getLoginName(), loginPARM.getPassword(), IpUtils.getIpAddr(request));
        TokenVo tokenVo = userService.getToken(user);
        return success(tokenVo);
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("账户清除")
    @DeleteMapping("/token")
    public ApiResponses<Void> removeToken() {

        return success(HttpStatus.NO_CONTENT);
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("账户密码修改")
    @PutMapping("/password")
    public ApiResponses<Void> updatePassword(@RequestBody @Validated PasswordPARM passwordPARM) {

        userService.updatePassword(currentUid(), passwordPARM.getOldPassword(), passwordPARM.getNewPassword());
        return success();
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("账户详情")
    @GetMapping("/info")
    public ApiResponses<UserDetailsVo> accountInfo() {

        Integer uid = currentUid();
        UserDetailsVo userDetails = userService.getUserDetails(uid);
        return success(userDetails);
    }

    @Resources(AuthTypeEnum.LOGIN)
    @ApiOperation("账户菜单")
    @GetMapping("/menus")
    public ApiResponses<List<MenuTreeVo>> menus() {

        List<MenuTreeVo> menuTrees = menuService.getUserPermMenus(currentUid());
        return success(menuTrees);
    }

}