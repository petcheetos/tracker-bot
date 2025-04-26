package edu.java.bot.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Bot Service API",
                version = "v1",
                description = "API для обработки обновлений ссылок и уведомлений пользователей Telegram"
        )
)
public class OpenApiConfig {
}
