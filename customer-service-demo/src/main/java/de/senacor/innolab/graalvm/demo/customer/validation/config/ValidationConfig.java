package de.senacor.innolab.graalvm.demo.customer.validation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration(proxyBeanMethods = false)
public class ValidationConfig {

    private String url;

    public ValidationConfig(@Value("${validation.url}") String url) {
        this.url = url;
    }
}
