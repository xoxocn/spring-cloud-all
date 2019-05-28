package com.xoxo.product.enums;

import lombok.Getter;

/**
 * @Package com.xoxo.order.enums
 * @Description 响应枚举
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-12-03 17:56
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXiST("1","商品不存在"),
    PRODUCT_STOCK_ERROR("2","库存有误"),
    PRODUCT_PARAM_ERROR("3","商品参数有误"),
    ;

    private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
