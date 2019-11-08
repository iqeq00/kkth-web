package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.NewsMapper;
import com.kkth.web.model.entity.News;
import com.kkth.web.service.NewsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻通讯 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
