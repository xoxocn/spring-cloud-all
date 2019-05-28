package com.xoxo.product.config.aspect;

import java.lang.annotation.*;

/**
 * @author yindeyue 需要打印日志得标志注解
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShowLog {
	/**
	 * 需要打印日志得环境
	 */
	String[] environment();
}
