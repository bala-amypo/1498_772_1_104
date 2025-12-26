package com.example.demo.config;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class WebMvcConfig {

    @Bean
    public OpenApiCustomiser jwtOpenApiCustomiser() {
        return openApi -> {

            // 🔐 Define JWT Bearer scheme
            openApi.getComponents().addSecuritySchemes(
                    "bearerAuth",
                    new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
            );

            // 🔒 Apply globally
            openApi.addSecurityItem(
                    new SecurityRequirement().addList("bearerAuth")
            );
        };
    }
}
