package de.senacor.innolab.graalvm.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
public class RestTemplateClient {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
