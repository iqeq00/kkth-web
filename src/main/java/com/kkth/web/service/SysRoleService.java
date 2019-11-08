package com.kkth.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkth.web.model.entity.SysRole;
import com.kkth.web.model.vo.RoleVo;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色详情列表
     *
     * @param page
     * @param roleName
     * @return
     */
    IPage<RoleVo> pageRoleVo(Page<SysRole> page, String roleName);
}
