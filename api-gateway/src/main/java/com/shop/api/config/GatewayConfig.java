package com.shop.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/shop/customers/**")
                        .uri("lb://shop-service"))
                .route(p -> p
                        .path("/api/shop/orders/**")
                        .uri("lb://shop-service"))
                .route(p -> p
                        .path("/api/shop/products/**")
                        .uri("lb://shop-service"))
                .route(p -> p
                        .path("/api/shop/articles/**")
                        .uri("lb://shop-service"))
                .build();
    }
}

