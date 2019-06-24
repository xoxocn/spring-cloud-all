package com.xoxo.apigateway;

import com.xoxo.apigateway.filter.RequestTimeGatewayFilterFactory;
import com.xoxo.apigateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		String httpUri = "http://httpbin.org:80";
//		// @formatter:off
//		return builder.routes()
//				.route(r -> r.path("/xo1/**")
//						.filters(f -> f.filter(new RequestTimeFilter())
//								.addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
//						.uri("http://httpbin.org:80")
//						.order(0)
//						.id("customer_filter_router")
//				)
//				.build();
//		// @formatter:on
//	}


//	@RequestMapping("/fallback")
//	public Mono<String> fallback() {
//		return Mono.just("fallback");
//	}


	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}


//	@Bean
//	public TokenFilter tokenFilter(){
//		return new TokenFilter();
//	}


}
