package com.xoxo.product.service;

import com.product.common.DecreaseStockInput;
import com.xoxo.product.model.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiehua
 * @since 2019-04-30
 */
public interface IProductInfoService extends IService<ProductInfo> {

    boolean decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
