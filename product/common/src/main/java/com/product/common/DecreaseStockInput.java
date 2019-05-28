package com.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package com.product.common
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-05-14 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

}
