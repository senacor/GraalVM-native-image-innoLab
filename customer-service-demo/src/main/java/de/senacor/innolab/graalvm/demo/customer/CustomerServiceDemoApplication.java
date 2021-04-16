package de.senacor.innolab.graalvm.demo.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class CustomerServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceDemoApplication.class, args);
	}

}
