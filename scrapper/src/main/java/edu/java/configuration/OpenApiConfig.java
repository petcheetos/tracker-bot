package edu.java.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Scrapper Service API",
                version = "v1",
                description = "API для взаимодействия с микросервисом Scrapper. Scrapper занимается хранением и обработкой отслеживаемых ссылок пользователей Telegram-бота."
        )
)
public class OpenApiConfig {
}
