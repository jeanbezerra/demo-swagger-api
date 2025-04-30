package com.jeanbezerra.calculadora.api;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.jeanbezerra")) // ajuste para o seu pacote base
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .securityContexts(List.of(securityContext()))
            .securitySchemes(List.of(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("calculadora-api")
            .description("Projeto que realiza cálculos para servir como demonstração")
            .version("1.0.0-RELEASE")
            .license("Apache License")
            .licenseUrl("https://jeanbezerra.com")
            .termsOfServiceUrl("https://jeanbezerra.com")
            .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer Authentication", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(List.of(new SecurityReference("Bearer Authentication",
                new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})))
            .build();
    }
}
