package de.senacor.innolab.graalvm.customer.config;

import de.senacor.innolab.graalvm.customer.CustomErrorResponse;
import de.senacor.innolab.graalvm.customer.controller.openapiMock.model.CustomerDto;
import de.senacor.innolab.graalvm.customer.validation.AgeValidationRequest;
import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.ProxyHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.PathVariable;

@NativeHint(
        types = @TypeHint(types = {
                AgeValidationRequest.class,
                CustomerDto.class,
                CustomErrorResponse.class,
                PostgreSQLDialect.class
        }),
        proxies = @ProxyHint(types = {
                PathVariable.class,
                SynthesizedAnnotation.class
        })
)
@Configuration(proxyBeanMethods = false)
public class NativeHintConfiguration {
}
