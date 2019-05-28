package com.xoxo.product.service.impl;

import com.xoxo.product.model.ProductCategory;
import com.xoxo.product.mapper.ProductCategoryMapper;
import com.xoxo.product.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiehua
 * @since 2019-05-06
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

}
