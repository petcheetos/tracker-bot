package edu.java.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Ответ с полным списком ссылок для чата")
public record ListLinkResponse(
        @Schema(description = "Список отслеживаемых ссылок")
        List<LinkResponse> links,

        @Schema(description = "Общее количество ссылок", example = "2")
        int size
) {}