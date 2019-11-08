package com.kkth.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkth.web.mapper.ProductionMapper;
import com.kkth.web.model.entity.Production;
import com.kkth.web.service.ProductionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品推介 服务实现类
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {

}
