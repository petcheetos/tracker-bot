package commands;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.spbstu.commands.*;
import ru.spbstu.configurations.CommandConfig;
import ru.spbstu.services.BotCommandService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCommandTest {
    @Mock
    private final CommandConfig commandConfig = new CommandConfig();

    @InjectMocks
    private final BotCommandService botCommandService = new BotCommandService(commandConfig);

    private final Command start = new StartCommand();
    private final Command list = new ListCommand();
    private final Command track = new TrackCommand();
    private final Command untrack = new UntrackCommand();
    private final List<Command> commandList = List.of(start, list, track, untrack);
    private final Command help = new HelpCommand(commandList);

    @Test
    void testHelpName() {
        assertThat(help.name()).isNotBlank().contains("/help");
    }

    @Test
    void testHelpDescription() {
        assertThat(help.description()).isNotBlank();
    }

    @Test
    void testMapContainsHelp() {
        assertTrue(botCommandService.getCommandMap().containsKey("/help"));
    }

    @Test
    void testMapContainsAllCommandsHelpContains() {
        for (Command command : commandList) {
            assertTrue(botCommandService.getCommandMap().containsKey(command.name()));
        }
    }
}
