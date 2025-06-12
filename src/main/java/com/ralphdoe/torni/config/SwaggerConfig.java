package com.ralphdoe.torni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // Cambia el paquete base de tus controladores
                .apis(RequestHandlerSelectors.basePackage("com.ralphdoe.torni.adapter.inbound.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Torni-App API")
                .description("API para gestión de torneos")
                .version("v1")
                .contact(new springfox.documentation.service.Contact(
                        "Equipo Torni-App",
                        "https://github.com/cristianlambis/torni-app",
                        "soporte@torniapp.com"
                ))
                .build();
    }
}
