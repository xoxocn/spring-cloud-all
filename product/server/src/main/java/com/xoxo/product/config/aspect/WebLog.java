package com.xoxo.product.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Web统一日志打印类
 * 
 * @author yindeyue
 * @date 2018年4月24日 上午11:03:13
 */
@Aspect
@Component
@Order(-5)
@SuppressWarnings("rawtypes")
@Slf4j
public class WebLog {

	@Resource
	private Environment systemEnv;

	@Around(value = "@annotation(com.xoxo.product.config.aspect.ShowLog)")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		/**
		 * 获得注解上标注得系统环境
		 */
		String[] env = method.getAnnotation(ShowLog.class).environment();
		List<String> asList = Arrays.asList(env);
		/**
		 * 获得系统当前环境
		 */
		String[] property = systemEnv.getActiveProfiles();
		if (asList.contains(property[0])) {
			// 记录下请求内容
			log.info("------------------------------------------");
			log.info("URL : " + request.getRequestURL().toString());
			log.info("HTTP_METHOD : " + request.getMethod());
			log.info("IP : " + request.getRemoteAddr());
			log.info("CLASS_METHOD : " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "."
					+ proceedingJoinPoint.getSignature().getName());
			log.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));
			Object result = proceedingJoinPoint.proceed();
			// 处理完请求，返回内容
			log.info("RESPONSE : " + result);
			log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime));
			log.info("------------------------------------------");
		}
	}

	@AfterReturning(value = "@annotation(com.xoxo.product.config.aspect.ShowLog)", returning = "ret")
	public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String methodName2 = joinPoint.getSignature().toLongString();
		int indexOf = methodName2.indexOf("(");
		String substring = methodName2.substring(indexOf + 1, methodName2.length() - 1);
		String[] split = substring.split(",");
		Object[] arguments = joinPoint.getArgs();
		Class[] classs = new Class[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			classs[i] = Class.forName(split[i]);
		}
		Class targetClass = Class.forName(targetName);
		Method method = targetClass.getMethod(methodName, classs);
		/**
		 * 获得注解上标注得系统环境
		 */
		String[] env = method.getAnnotation(ShowLog.class).environment();
		List<String> asList = Arrays.asList(env);
		/**
		 * 获得系统当前环境
		 */
		String[] property = systemEnv.getActiveProfiles();
		if (asList.contains(property[0])) {

		}
	}

}