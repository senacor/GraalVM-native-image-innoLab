package de.senacor.innolab.graalvm.demo.validation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @RequestMapping("/")
    fun index() = "Greetings from validation-service-demo!"

}
