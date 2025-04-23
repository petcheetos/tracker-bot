package edu.java.scrapper.services;

import edu.java.exception.RequestException;
import edu.java.scrapper.IntegrationTest;
import edu.java.services.jpa.JpaChatService;
import edu.java.services.jpa.JpaLinkService;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(properties = "app.database-access-type=jpa")
public class JpaServiceTest extends IntegrationTest {

    @Autowired
    private JpaChatService chatService;

    @Autowired
    private JpaLinkService linkService;

    @Test
    @Transactional
    @Rollback
    void testDeleteChat() {
        chatService.registerChat(10L);
        assertDoesNotThrow(() -> chatService.deleteChat(10L));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetLinks() {
        chatService.registerChat(10L);
        linkService.addLink(10L, URI.create("https://github.com/petcheetos/backend-java-course"));
        assertDoesNotThrow(() -> linkService.getLinks(10L));
    }

    @Test
    @Transactional
    public void testGetLinksWithNoChat() {
        assertThrows(RequestException.class, () -> linkService.getLinks(10L));
    }

    @Test
    @Transactional
    @Rollback
    void testAddLink() {
        chatService.registerChat(10L);
        assertDoesNotThrow(() -> linkService.addLink(
                10L,
                URI.create("https://github.com/petcheetos/backend-java-course")
        ));
    }

    @Test
    @Transactional
    @Rollback
    public void testAddLinkWithIncorrectLink() {
        chatService.registerChat(10L);
        assertThrows(RequestException.class, () -> linkService.addLink(10L, URI.create("1")));
    }

    @Test
    @Transactional
    @Rollback
    public void testAddLinkWithNoChat() {
        assertThrows(
                RequestException.class,
                () -> linkService.addLink(10L, URI.create("https://github.com/petcheetos/backend-java-course"))
        );
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteLink() {
        chatService.registerChat(10L);
        linkService.addLink(10L, URI.create("https://github.com/petcheetos/backend-java-course"));
        assertDoesNotThrow(() -> linkService.deleteLink(
                10L,
                URI.create("https://github.com/petcheetos/backend-java-course")
        ));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteLinkWithNoChat() {
        assertThrows(
                RequestException.class,
                () -> linkService.deleteLink(10L, URI.create("https://github.com/petcheetos/backend-java-course"))
        );
    }
}
