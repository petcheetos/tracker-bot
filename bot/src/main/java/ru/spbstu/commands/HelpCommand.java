package ru.spbstu.commands;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelpCommand implements Command {
    List<Command> commandList;

    public HelpCommand(List<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public String name() {
        return "/help";
    }

    @Override
    public String description() {
        return "Show commands";
    }

    @Override
    public String execute(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Commands:\n");
        for (Command cmd : commandList) {
            stringBuilder.append("\t- ").append(cmd.name()).append(" - ").append(cmd.description()).append("\n");
        }
        return stringBuilder.toString();
    }
}
