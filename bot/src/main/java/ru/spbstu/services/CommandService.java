package ru.spbstu.services;

import com.pengrad.telegrambot.model.Update;
import ru.spbstu.commands.Command;

import java.util.Map;

public interface CommandService {

    String createResponse(Update update);

    Map<String, Command> getCommandMap();
}
