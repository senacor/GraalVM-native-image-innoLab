package de.senacor.innolab.graalvm.validation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

@NativeHint(
        types = @TypeHint(types = {
                HttpStatus.class,
                Instant.class,
                LocalDate.class,
                Map.class,
                ServletWebRequest.class,
                WebRequest.class
        })
)
@Configuration(proxyBeanMethods = false)
public class NativeHintConfiguration {
}
