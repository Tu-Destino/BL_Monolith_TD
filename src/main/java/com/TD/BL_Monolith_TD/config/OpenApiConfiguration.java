package com.TD.BL_Monolith_TD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "DestinoAPI",
                version = "3.1",
                description = "<b>DestinoAPI:</b> The Ultimate Information Hub for <i>Tu Destino</i>.<br>Providing precise information, advanced filtering, and seamless email dispatch services.<br>Tailored for a superior user experience, ensuring all the information you need is just a click away.",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Support",
                        email = "support@tudestino.com",
                        url = "https://www.tudestino.coalmd.com/"
                )
        )
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server()
                        .url("https://tudestinoresourse.coalmd.com/TD/api/v1")
                        .description("Production Server"))
                .info(new Info()
                        .title("DestinoAPI")
                        .version("3.1")
                        .description("<b>DestinoAPI:</b> The Ultimate Information Hub for <i>Tu Destino</i>.<br>Providing precise information, advanced filtering, and seamless email dispatch services.<br>Tailored for a superior user experience, ensuring all the information you need is just a click away.")
                        .contact(new Contact()
                                .name("Support")
                                .email("support@tudestino.com")
                                .url("https://www.tudestino.coalmd.com/")));
    }
}
