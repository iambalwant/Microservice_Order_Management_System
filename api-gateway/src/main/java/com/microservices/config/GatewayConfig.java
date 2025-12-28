package com.microservices.config;

import com.microservices.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("AUTH-SERVICE", r -> r.path("/authenticate/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVICE"))
                .route("PRODUCT-SERVICE", r -> r.path("/api/v1/product/**").filters(f -> f.filter(filter)).uri("lb://PRODUCT-SERVICE"))
                .route("PAYMENT-SERVICE", r -> r.path("/api/v1/payment/**").filters(f -> f.filter(filter)).uri("lb://PAYMENT-SERVICE"))
                .route("INVENTORY-SERVICE", r -> r.path("/api/v1/inventory/**").filters(f -> f.filter(filter)).uri("lb://INVENTORY-SERVICE"))
                .route("ORDER-SERVICE", r -> r.path("/api/v1/orderService/**").filters(f -> f.filter(filter)).uri("lb://ORDER-SERVICE")).build();
    }
}
