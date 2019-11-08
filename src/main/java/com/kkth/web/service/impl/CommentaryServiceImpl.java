package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.CommentaryMapper;
import com.kkth.web.model.entity.Commentary;
import com.kkth.web.service.CommentaryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标示解说系统 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class CommentaryServiceImpl extends ServiceImpl<CommentaryMapper, Commentary> implements CommentaryService {

}
