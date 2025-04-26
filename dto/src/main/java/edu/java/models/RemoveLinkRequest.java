package edu.java.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.net.URI;

@Schema(description = "Запрос на удаление ссылки из чата")
public record RemoveLinkRequest(
        @Schema(description = "URI удаляемой ссылки", example = "https://github.com/user/repo")
        @NotNull URI link
) {}
