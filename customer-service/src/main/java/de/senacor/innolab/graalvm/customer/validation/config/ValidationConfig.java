package de.senacor.innolab.graalvm.customer.validation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration(proxyBeanMethods = false)
public class ValidationConfig {

    @Value("${validation.url}")
    private String url;
}
