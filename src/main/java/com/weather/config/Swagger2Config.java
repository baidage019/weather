package com.weather.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    private static final String HOST = "host.example.com";
    private static final String BASE_PACK = "org.springframework.boot";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).host(HOST).select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage(BASE_PACK)))
                .build();
    }
}
