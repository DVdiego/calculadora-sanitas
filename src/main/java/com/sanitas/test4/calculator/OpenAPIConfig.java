package com.sanitas.test4.calculator;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${calculator.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI calculatorAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in development environment");

        Contact contact = new Contact();
        contact.setEmail("diego.borja.mail@gmail.com");
        contact.setName("DVdiego");
        contact.setUrl("https://github.com/DVdiego/calculadora-sanitas/");

        Info info = new Info()
                .title("Calculator API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to perform addition and subtraction operations.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
