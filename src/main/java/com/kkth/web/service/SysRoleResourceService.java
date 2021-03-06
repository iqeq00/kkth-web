package com.kkth.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkth.web.model.entity.SysRoleResource;

/**
 * <p>
 * 角色资源关系表 服务类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
public interface SysRoleResourceService extends IService<SysRoleResource> {

    void saveTransaction();
}
