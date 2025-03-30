package commands;


import org.junit.jupiter.api.Test;
import ru.spbstu.commands.Command;
import ru.spbstu.commands.ListCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class ListCommandTest {
    private final Command list = new ListCommand();

    @Test
    void testListName() {
        assertThat(list.name()).isNotBlank().contains("/list");
    }

    @Test
    void testListDescription() {
        assertThat(list.description()).isNotBlank();
    }

    //void testExecute()

    //void testExecuteWithEmptyList()
}
