package com.hb.authen_demo.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfiguration {

//    @Bean
//    public OpenAPI apiDocConfig() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Business Case")
//                        .description("API: User Management")
//                        .version("0.0.1")
//                        .contact(
//                                new Contact()
//                                        .name("Annisa")
//                                        .email("hidayati.ann@gmail.com")
//                        ));
//    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("API REST")
                        .description("This ia an API REST.")
                        .version("1.0").contact(new Contact().name("Annisa Hidayati")
                                .email("hidayati.ann@gmail.com").url("https://github.com/ahidayati"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
