package com.mes.priceintelligence.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by mesar on 6/21/2020
 */
@ConfigurationProperties
public class UriConfiguration {
    private String httpbin = "http://httpbin.org:80";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
