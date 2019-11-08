package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.ParkMapper;
import com.kkth.web.model.entity.Park;
import com.kkth.web.service.ParkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公园概况 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class ParkServiceImpl extends ServiceImpl<ParkMapper, Park> implements ParkService {

}
