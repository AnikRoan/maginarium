package com.aimaginarium.config.swagger_config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Aimaginarium",
                        email = "aimaginarium@gmail.com",
                        url = "https://aimaginarium"
                ),
                description = " OpenApi Documentation for Aimaginarium",
                title = "Aimaginarium API SPECIFICATION",
                version = "1.0.0",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production Server",
                        url = "https://aimaginarium.com"
                )
        }
)
public class OpenApiSwaggerConfig {
}
