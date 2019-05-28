package com.xoxo.product.config.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @Package com.xoxo.product.config.filter
 * @Description C - Cross  O - Origin  R - Resource  S - Sharing
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-05-14 11:05
 */
@Configuration
public class CorssFiler {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        //配置跨域cookie
        config.setAllowCredentials(true);
        //配置资源域
        config.setAllowedOrigins(Arrays.asList("*"));
        //配置预置请求头
        config.setAllowedHeaders(Arrays.asList("*"));
        //配置请求方法
        config.setAllowedHeaders(Arrays.asList("*"));
        //配置跨域缓存，客户端
        config.setMaxAge(300l);

        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
}
