package com.jigowatts.monolith.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "data.from")
public class BatchContext {
    private String url;
}
