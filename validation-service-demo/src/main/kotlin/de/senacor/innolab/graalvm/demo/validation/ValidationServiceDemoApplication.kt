package de.senacor.innolab.graalvm.demo.validation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ValidationServiceDemoApplication

fun main(args: Array<String>) {
	runApplication<ValidationServiceDemoApplication>(*args)
}
