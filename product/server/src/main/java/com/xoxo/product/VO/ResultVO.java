package com.xoxo.product.VO;

import lombok.Data;

/**
 * @Package com.xoxo.product.VO
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-04-30 14:37
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

}
