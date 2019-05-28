package com.xoxo.product.utils;


import com.xoxo.product.VO.ResultVO;
import com.xoxo.product.enums.ResponseEnum;
import com.xoxo.product.exception.CommonException;

/**
 * @Package com.xoxo.productserver.utils
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-12-03 15:28
 */
public class ResultVOUtil {

    public static <T>ResultVO success(T data){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode("200");
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static <T>ResultVO exception(CommonException e){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(e.getCode());
        resultVO.setMsg(e.getMessage());
        return resultVO;
    }

    public static <T>ResultVO exception(ResponseEnum responseEnum){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(responseEnum.getCode());
        resultVO.setMsg(responseEnum.getMsg());
        return resultVO;
    }
}
