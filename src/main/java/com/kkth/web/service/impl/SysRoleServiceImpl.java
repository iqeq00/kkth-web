package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.framework.converter.BeanConverter;
import com.kkth.framework.utils.TypeUtils;
import com.kkth.web.mapper.SysRoleMapper;
import com.kkth.web.model.entity.SysRole;
import com.kkth.web.model.entity.SysRoleMenu;
import com.kkth.web.model.vo.RoleVo;
import com.kkth.web.service.SysRoleMenuService;
import com.kkth.web.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Override
    public IPage<RoleVo> pageRoleVo(Page<SysRole> page, String roleName) {
        IPage<SysRole> rolePage = lambdaQuery().like(StringUtils.isNotEmpty(roleName), SysRole::getRoleName, roleName).page(page);
        return rolePage.convert(role -> {
            RoleVo roleVo = BeanConverter.convert(RoleVo.class, role);
            List<SysRoleMenu> list = roleMenuService.lambdaQuery()
                    .select(SysRoleMenu::getMenuId)
                    .eq(SysRoleMenu::getRoleId, role.getId()).list();
            List<Integer> ids = new ArrayList<>();
            list.forEach(val -> {
                ids.add(val.getMenuId());
            });
            roleVo.setMenuIds(ids);
            return roleVo;
        });
    }
}
