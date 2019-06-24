package com.xoxo.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.product.common.DecreaseStockInput;
import com.xoxo.product.VO.ProductInfoVO;
import com.xoxo.product.VO.ProductVO;
import com.xoxo.product.VO.ResultVO;
import com.xoxo.product.enums.ProductStatusEnum;
import com.xoxo.product.enums.ResultEnum;
import com.xoxo.product.exception.ProductException;
import com.xoxo.product.form.ProductForm;
import com.xoxo.product.model.ProductCategory;
import com.xoxo.product.model.ProductInfo;
import com.xoxo.product.service.IProductCategoryService;
import com.xoxo.product.service.IProductInfoService;
import com.xoxo.product.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiehua
 * @since 2019-04-30
 */
@Slf4j
@RestController
@RequestMapping("/product-info")
public class ProductInfoController {

    @Resource
    private IProductInfoService productInfoService;
    @Resource
    private IProductCategoryService productCategoryService;

    /**
     * 1.查询所有在架的商品
     * 2.查询类目type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/findProductUpAll")
    public ResultVO<List<ProductVO>> findProductUpAll() {
        QueryWrapper<ProductInfo> productInfoQueryWrapper
                = new QueryWrapper<>();
        productInfoQueryWrapper
                .eq("product_status", ProductStatusEnum.UP.getCode());
        List<ProductInfo> productInfoList = productInfoService
                .list(productInfoQueryWrapper);
        Map<Integer, List<ProductInfo>> integerListMap = productInfoList.stream()
                .collect(Collectors.groupingBy(ProductInfo::getCategoryType));
        QueryWrapper<ProductCategory> productCategoryQueryWrapper
                = new QueryWrapper<>();
        productCategoryQueryWrapper
                .in("category_type", integerListMap.keySet());
        List<ProductCategory> productCategoryList
                = productCategoryService.list(productCategoryQueryWrapper);
        List<ProductVO> productVOList = productCategoryList.stream().map(productCategory -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> collect = integerListMap.get(productCategory.getCategoryType()).stream().map(productInfo -> {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, productInfoVO);
                return productInfoVO;
            }).collect(Collectors.toList());
            productVO.setProductInfoVOList(collect);
            return productVO;
        }).collect(Collectors.toList());
        return ResultVOUtil.success(productVOList);
    }

    @PostMapping("/saveProductInfo")
    public void saveProductInfo(@Valid ProductForm productForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建产品】参数不正确, productForm={}",productForm);
            throw new ProductException(ResultEnum.PRODUCT_PARAM_ERROR.getCode()
            ,bindingResult.getFieldError().getDefaultMessage());
        }
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productForm,productInfo);
        productInfoService.saveOrUpdate(productInfo);
//        return ResultVOUtil.success(true);
    }

    @GetMapping("decreaseStock")
    public boolean decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        return productInfoService.decreaseStock(decreaseStockInputList);
    }

    @GetMapping("checkBigDecimal")
    public void checkBigDecimal(@RequestParam BigDecimal bigDecimal){
        log.info(bigDecimal.toString());
    }
}
