package de.senacor.innolab.graalvm.validation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @RequestMapping("/")
    fun index() = "Greetings from Validation-Service!"

}
