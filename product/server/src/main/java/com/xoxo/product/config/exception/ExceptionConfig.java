package com.xoxo.product.config.exception;

import com.xoxo.product.VO.ResultVO;
import com.xoxo.product.enums.ResponseEnum;
import com.xoxo.product.exception.RespBaseException;
import com.xoxo.product.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Package com.xoxo.product.config.exception
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-05-14 11:47
 */
@Slf4j
@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(Exception e){
        log.warn("异常：{}",e.getMessage());
        return ResultVOUtil.exception(ResponseEnum.server_error);
    }

    @ExceptionHandler(value= RespBaseException.class)
    public ResultVO exceptionHandler(RespBaseException e){
        log.warn("异常：{}",e.getMessage());
        return ResultVOUtil.exception(e);
    }
}
