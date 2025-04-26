package edu.java.bot.controllers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.models.LinkUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/updates")
@Tag(name = "Bot Controller", description = "Контроллер для обработки обновлений ссылок и рассылки уведомлений пользователям Telegram")
public class BotController {

    private final TelegramBot telegramBot;

    @Operation(
            summary = "Обработка уведомлений об обновлениях ссылок",
            description = "Принимает информацию об изменении отслеживаемых ссылок и рассылает уведомления в Telegram по списку идентификаторов чатов."
    )
    @PostMapping
    public void processUpdate(@RequestBody LinkUpdateRequest linkRequest) {
        log.info(linkRequest);
        List<Long> tgChatIds = linkRequest.tgChatIds();
        for (var chatId : tgChatIds) {
            telegramBot.execute(new SendMessage(chatId, linkRequest.description() + " " + linkRequest.url()));
        }
    }
}
