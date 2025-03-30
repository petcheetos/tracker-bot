package commands;

import org.junit.jupiter.api.Test;
import ru.spbstu.commands.Command;
import ru.spbstu.commands.TrackCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackCommandTest {
    private final Command track = new TrackCommand();

    @Test
    void testStartName() {
        assertThat(track.name()).isNotBlank().contains("/track");
    }

    @Test
    void testStartDescription() {
        assertThat(track.description()).isNotBlank();
    }

    //void testExecute()
}
