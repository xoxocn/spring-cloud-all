package com.xoxo.product.exception;


import com.xoxo.product.enums.ResultEnum;

/**
 * @Package com.xoxo.productserver.exception
 * @Description 商品异常
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-12-04 19:53
 */
public class ProductException extends CommonException {


    public ProductException(String code, String message) {
        super(code,message);
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getCode(),resultEnum.getMessage());
    }
}
