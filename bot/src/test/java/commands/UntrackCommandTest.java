package commands;

import org.junit.jupiter.api.Test;
import ru.spbstu.commands.Command;
import ru.spbstu.commands.UntrackCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class UntrackCommandTest {
    private final Command untrack = new UntrackCommand();

    @Test
    void testStartName() {
        assertThat(untrack.name()).isNotBlank().contains("/untrack");
    }

    @Test
    void testStartDescription() {
        assertThat(untrack.description()).isNotBlank();
    }

    //void testExecute()
}
