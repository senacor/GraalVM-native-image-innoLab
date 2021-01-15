package de.senacor.innolab.graalvm.validation

import io.kotlintest.matchers.types.beNull
import io.kotlintest.shouldBe
import io.kotlintest.shouldNot
import io.kotlintest.specs.StringSpec
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.WebApplicationContextRunner

class ValidationServiceApplicationTests : StringSpec({

    "The web application context loads" {
        WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ValidationServiceApplication::class.java)).run {
                it.startupFailure shouldBe null
                it.getBean(ValidationServiceApplication::class.java) shouldNot beNull()
            }
    }
})
