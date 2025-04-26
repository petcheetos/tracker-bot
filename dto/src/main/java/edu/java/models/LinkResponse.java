package edu.java.models;

import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;

@Schema(description = "Ответ с данными о ссылке")
public record LinkResponse(
        @Schema(description = "Уникальный идентификатор ссылки", example = "1")
        long id,

        @Schema(description = "URI отслеживаемой ссылки", example = "https://stackoverflow.com/questions/123456")
        URI url
) {}