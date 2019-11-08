package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.ParkinfoMapper;
import com.kkth.web.model.entity.Parkinfo;
import com.kkth.web.service.ParkinfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公园信息管理 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class ParkinfoServiceImpl extends ServiceImpl<ParkinfoMapper, Parkinfo> implements ParkinfoService {

}
