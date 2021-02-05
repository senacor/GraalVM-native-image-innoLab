package com.senacor.innolab.graalvm.openapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name ="Graal VM InnoLab@Senacor",description = "Dummy credit check microservice")
        },
        info = @Info(
                title = "Credit check service API",
                version = "0.0.1",
                contact = @Contact(
                        name = "Simon Gyimah",
                        email = "simon.gyimah@senacor.com"
                ),
                license =@License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class CreditCheckServiceApplication  extends Application {
}
