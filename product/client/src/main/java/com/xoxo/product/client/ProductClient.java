package com.xoxo.product.client;

import com.product.common.DecreaseStockInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Package com.xoxo.product.client
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-05-14 14:52
 */
@FeignClient(name  = "product-server")
public interface ProductClient {
    @PostMapping("/product-info/decreaseStock")
    boolean decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

}
