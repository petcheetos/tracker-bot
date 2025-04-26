package edu.java.controllers;

import edu.java.models.*;
import edu.java.services.ChatService;
import edu.java.services.LinkService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Scrapper Controller", description = "Управление чатами и отслеживаемыми ссылками")
public class ScrapperController {

    private final ChatService chatService;
    private final LinkService linkService;

    @Operation(
            summary = "Регистрация чата",
            description = "Регистрирует Telegram-чат по его ID для последующего добавления ссылок"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Чат зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Чат не найден", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("/tg-chat/{id}")
    public String registerChat(@PathVariable("id") long id) {
        chatService.registerChat(id);
        return "Chat registered";
    }

    @Operation(
            summary = "Удаление чата",
            description = "Удаляет чат и связанные с ним ссылки"
    )
    @DeleteMapping("/tg-chat/{id}")
    public String deleteChat(@PathVariable("id") long id) {
        chatService.deleteChat(id);
        return "Chat deleted";
    }

    @Operation(
            summary = "Получение ссылок чата",
            description = "Возвращает все отслеживаемые ссылки для указанного Telegram-чата"
    )
    @GetMapping("/links")
    public ListLinkResponse getLinks(@RequestHeader("Tg-Chat-Id") long chatId) {
        List<LinkResponse> links = linkService.getLinks(chatId);
        return new ListLinkResponse(links, links.size());
    }

    @Operation(
            summary = "Добавление ссылки",
            description = "Добавляет ссылку в список отслеживаемых для Telegram-чата"
    )
    @PostMapping("/links")
    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") long chatId, @RequestBody AddLinkRequest request) {
        return linkService.addLink(chatId, request.link());
    }

    @Operation(
            summary = "Удаление ссылки",
            description = "Удаляет ссылку из отслеживания для Telegram-чата"
    )
    @DeleteMapping("/links")
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") long chatId, @RequestBody RemoveLinkRequest request) {
        return linkService.deleteLink(chatId, request.link());
    }
}
