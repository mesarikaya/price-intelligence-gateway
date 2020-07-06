package com.mes.priceintelligence.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/**
 * Created by mesar on 6/20/2020
 */
@Configuration
public class RoutingConfig {

    @Value("${django_app_url}")
    private String djangoAppBaseUrl;

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration){
        String httpUri = uriConfiguration.getHttpbin();
        return builder.routes()
                .route(r -> r.method(HttpMethod.POST)
                        .and()
                        .path("/api/token/obtain/")
                        .uri(djangoAppBaseUrl)
                        .id("django-web-product-scraper-microservice"))
                .route(r -> r.path("/api/jobs/productdetails/activate/all", "/api/jobs/productdetails/activate/*")
                        .uri("lb://price-intelligence-job-scheduler")
                        .id("price-intelligence-job-scheduler"))
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .build();
    }
}
