package com.TD.BL_Monolith_TD.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "DestinoAPI",
                version = "3.1",
                description = "<b>DestinoAPI:</b> The Ultimate Information Hub for <i>Tu Destino</i>.<br>Providing precise information, advanced filtering, and seamless email dispatch services.<br>Tailored for a superior user experience, ensuring all the information you need is just a click away.",
                contact = @Contact(
                        name = "Support",
                        email = "support@tudestino.com",
                        url = "https://tu-destino-v3-0-wed.vercel.app/"
                )
        )
       //, security = @SecurityRequirement(name = "basicAuth")
)

public class OpenApiConfiguration {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .components(new Components()
                                .addSecuritySchemes("basicAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")));
        }
}
