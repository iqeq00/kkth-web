package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.MuseumMapper;
import com.kkth.web.model.entity.Museum;
import com.kkth.web.service.MuseumService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博物馆基本情况 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class MuseumServiceImpl extends ServiceImpl<MuseumMapper, Museum> implements MuseumService {

}
