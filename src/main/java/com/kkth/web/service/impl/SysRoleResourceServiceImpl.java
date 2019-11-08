package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.SysRoleResourceMapper;
import com.kkth.web.model.entity.SysRoleResource;
import com.kkth.web.service.SysRoleResourceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色资源关系表 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {

    @Override
    public void saveTransaction() {

        throw new RuntimeException("报错了");
    }
}
