package com.manager.parkinglot.handler.resource.seoul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties(SeoulParkingLotResourceConfig.class)
@ConfigurationProperties(prefix = "parking.lot.api.seoul")
public class SeoulParkingLotResourceConfig {
    private String baseUrl;
    private String authKey;
    private long connectionTimeoutSeconds;
    private long readTimeoutSeconds;
    private long writeTimeoutSeconds;
}
