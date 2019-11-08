package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.GueststatMapper;
import com.kkth.web.model.entity.Gueststat;
import com.kkth.web.service.GueststatService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游客统计 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class GueststatServiceImpl extends ServiceImpl<GueststatMapper, Gueststat> implements GueststatService {

}
