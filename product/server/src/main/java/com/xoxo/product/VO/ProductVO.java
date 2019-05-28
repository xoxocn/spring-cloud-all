package com.xoxo.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Package com.xoxo.productserver.VO
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-12-03 14:34
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
