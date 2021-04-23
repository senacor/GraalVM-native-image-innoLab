package de.senacor.innolab.graalvm.demo.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class ValidationServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationServiceDemoApplication.class, args);
    }

}