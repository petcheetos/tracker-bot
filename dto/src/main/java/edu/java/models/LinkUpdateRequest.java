package edu.java.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.util.List;

@Schema(description = "Запрос на обновление ссылки с описанием события и списком чатов для уведомления")
public record LinkUpdateRequest(
        @Schema(description = "Уникальный идентификатор обновляемой ссылки", example = "1")
        long id,

        @Schema(description = "URL обновляемой ссылки", example = "https://github.com/user/repo")
        @NotNull URI url,

        @Schema(description = "Описание изменения, которое будет отправлено пользователям", example = "Новый коммит в репозитории")
        @NotEmpty String description,

        @Schema(description = "Список идентификаторов Telegram чатов, которым нужно отправить уведомление")
        @NotEmpty List<Long> tgChatIds
) {}
