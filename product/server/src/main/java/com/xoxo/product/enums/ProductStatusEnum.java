package com.xoxo.product.enums;

import lombok.Getter;

/**
 * @Package com.xoxo.productserver.enums
 * @Description 商品上下架状态
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-12-03 10:53
 */
@Getter
public enum ProductStatusEnum {
    UP("0","上架"),
    DOWN("1","下架")
    ;

    private String code;

    private String message;

    ProductStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
