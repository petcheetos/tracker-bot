package edu.java.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.net.URI;

@Schema(description = "Запрос на добавление новой ссылки в чат")
public record AddLinkRequest(
        @Schema(description = "URI добавляемой ссылки", example = "https://github.com/user/repo")
        @NotNull URI link
) {}
