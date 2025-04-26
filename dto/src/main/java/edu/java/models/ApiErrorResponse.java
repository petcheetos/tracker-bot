package edu.java.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Ответ при возникновении ошибки API")
public record ApiErrorResponse(
        @Schema(description = "Описание ошибки", example = "Некорректный запрос")
        String description,

        @Schema(description = "Код ошибки", example = "BAD_REQUEST")
        String code,

        @Schema(description = "Имя исключения", example = "RequestException")
        String exceptionName,

        @Schema(description = "Сообщение исключения", example = "Некорректный формат ссылки")
        String exceptionMessage,

        @Schema(description = "Стек вызовов исключения")
        List<String> stacktrace
) {}
