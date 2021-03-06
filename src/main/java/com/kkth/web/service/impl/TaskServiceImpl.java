package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.framework.redis.CacheableExpire;
import com.kkth.framework.redis.ExpireTime;
import com.kkth.web.mapper.TaskMapper;
import com.kkth.web.model.entity.Task;
import com.kkth.web.service.SysRoleResourceService;
import com.kkth.web.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

//    @Autowired
//    public SysRoleResourceService roleResourceService;
//
//    @Override
//    public void saveTransaction(Task task) {
//
//        this.save(task);
//        roleResourceService.saveTransaction();
//    }

    @Override
    public void saveTransaction(Task task) {

    }

    @Override
//    @Cacheable("taskPageCache")
    public IPage<Task> page(String taskName, String status, Date startTime, Date endTime, Page<Task> page) {

        System.out.println("进来了");
        LambdaQueryChainWrapper<Task> qw = lambdaQuery();
        if (StringUtils.isNotBlank(taskName)) {
            qw.like(Task::getTaskName, taskName);
        }
        if (StringUtils.isNotBlank(status)) {
            qw.eq(Task::getStatus, status);
        }
        if (null != startTime) {
            qw.ge(Task::getCreateTime, startTime);
        }
        if (null != endTime) {
            qw.le(Task::getCreateTime, endTime);
        }
        return qw.page(page);
    }
}
