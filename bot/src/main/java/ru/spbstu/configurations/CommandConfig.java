package ru.spbstu.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.spbstu.commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
@Configuration
public class CommandConfig {

    @Bean
    public Map<String, Command> commandMap() {
        Map<String, Command> commandMap = new HashMap<>();
        Command start = new StartCommand();
        Command list = new ListCommand();
        Command track = new TrackCommand();
        Command untrack = new UntrackCommand();
        Command help = new HelpCommand(List.of(start, list, track, untrack));
        commandMap.put("/start", start);
        commandMap.put("/list", list);
        commandMap.put("/track", track);
        commandMap.put("/untrack", untrack);
        commandMap.put("/help", help);
        return commandMap;
    }
}
