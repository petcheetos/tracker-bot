package edu.java.scrapper.repository;

import edu.java.models.LinkResponse;
import edu.java.repository.ChatRepository;
import edu.java.repository.jdbc.JdbcLinkRepository;
import edu.java.scrapper.IntegrationTest;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class JdbcLinkRepositoryTest extends IntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcLinkRepository linkRepository;
    @Autowired
    private ChatRepository chatRepository;

    @Test
    @Transactional
    @Rollback
    public void testAddLink() {
        long chatId = 1L;
        chatRepository.add(chatId);
        String url = "github.com";
        linkRepository.add(chatId, URI.create(url));
        Long count = jdbcTemplate.queryForObject("select count(id) from link where url = ?", Long.class, url);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveLinks() {
        chatRepository.add(1L);
        LinkResponse response = linkRepository.add(1L, URI.create("github.com"));
        linkRepository.remove(1L, response.url());
        assertFalse(linkRepository.findAllByChat(1L).contains(response));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAll() {
        long chatId = 1L;
        chatRepository.add(chatId);
        LinkResponse link = linkRepository.add(1L, URI.create("github.com"));
        List<LinkResponse> list = linkRepository.findAllByChat(chatId);
        assertThat(list).contains(link);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateLink() {
        String url = "github.com";
        Long linkId = jdbcTemplate.queryForObject("insert into link (url) values (?) returning id", Long.class, url);
        linkRepository.updateLastUpdatedAt(url, OffsetDateTime.now());
        assertThat(jdbcTemplate.queryForObject("select url from link where id = (?)", String.class, linkId)).isEqualTo(
                url);
    }
}
