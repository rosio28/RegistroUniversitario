package com.universidad.registro.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";
    private static final String SCHEME = "bearer";
    private static final String BEARER_FORMAT = "JWT";

       @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API del Sistema de Registro Universitario")
                        .version("1.0")
                        .description("Documentación completa de la API REST")
                        .termsOfService("https://universidad.com/terms")
                        .contact(new Contact()
                                .name("Soporte API")
                                .email("soporte@universidad.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación adicional")
                        .url("https://universidad.com/docs"))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme(SCHEME)
                                        .bearerFormat(BEARER_FORMAT)
                                        .in(SecurityScheme.In.HEADER)));
    }


    private Info getInfo() {
        return new Info()
                .title("API del Sistema de Registro Universitario")
                .description("Documentación de la API para el sistema de gestión universitaria")
                .version("1.0")
                .contact(new Contact()
                        .name("Equipo de Desarrollo")
                        .email("desarrollo@universidad.com"))
                .license(new License()
                        .name("Licencia MIT")
                        .url("https://opensource.org/licenses/MIT"));
    }
}