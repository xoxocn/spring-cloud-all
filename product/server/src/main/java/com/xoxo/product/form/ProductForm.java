package com.xoxo.product.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @Package com.xoxo.product.form
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-04-30 14:36
 */
@Data
public class ProductForm{

    /**
     * 商品名称
     */
    @NotEmpty(message = "产品名称必填")
    private String productName;

    /**
     * 单价
     */
    @NotEmpty(message = "产品单价必填")
    private BigDecimal productPrice;

    /**
     * 库存
     */
    @NotEmpty(message = "产品库存必填")
    private Integer productStock;

    /**
     * 描述
     */
    @NotEmpty(message = "产品描述必填")
    private String productDescription;

    /**
     * 小图
     */
    @NotEmpty(message = "产品小图必填")
    private String productIcon;

    /**
     * 商品状态,0正常1下架
     */
    @NotEmpty(message = "产品状态必填")
    private Integer productStatus;

    /**
     * 类目编号
     */
    @NotEmpty(message = "类目必填")
    private Integer categoryType;

}
