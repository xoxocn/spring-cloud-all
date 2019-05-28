package com.xoxo.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.product.common.DecreaseStockInput;
import com.xoxo.product.model.ProductInfo;
import com.xoxo.product.mapper.ProductInfoMapper;
import com.xoxo.product.service.IProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xoxo.product.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiehua
 * @since 2019-04-30
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

    @Override
    public boolean decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        return false;
    }
}
