package com.kkth.web.shiro;

import com.kkth.framework.enums.ErrorCodeEnum;
import com.kkth.framework.utils.ApiAssert;
import com.kkth.framework.utils.JWTUtils;
import com.kkth.framework.utils.TypeUtils;
import com.kkth.web.service.SysResourceService;
import com.kkth.web.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * JWT Realm 适用于shiro
 *
 * @author lichee
 */
@Service
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysResourceService resourceService;

    /**
     * 限定这个Realm只支持我们自定义的JWT Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，鉴权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer uid = JWTUtils.getUid(principals.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleIds = userService.getRoleIds(uid).stream().map(TypeUtils::castToString).collect(Collectors.toSet());
        simpleAuthorizationInfo.addRoles(roleIds);
        simpleAuthorizationInfo.addStringPermissions(resourceService.getUserPerms(uid));
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getPrincipal();
        // 判断Token是否过期
        ApiAssert.isFalse(ErrorCodeEnum.UNAUTHORIZED, JWTUtils.isExpired(token));
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
