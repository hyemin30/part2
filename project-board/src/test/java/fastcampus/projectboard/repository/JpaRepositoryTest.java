package fastcampus.projectboard.repository;

import fastcampus.projectboard.config.JpaConfig;
import fastcampus.projectboard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("testdb")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Profile에 지정한 db로 테스트하기 위함
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) // JpaAuditing 동작 위해서 import
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void given_when_then() {
        //given

        //when
        List<Article> articles = articleRepository.findAll();

        //then
        Assertions.assertThat(articles).isNotNull().hasSize(100);
    }
    @DisplayName("insert 테스트")
    @Test
    void insertTest() {
        //given

        //when
        List<Article> articles = articleRepository.findAll();

        //then
        Assertions.assertThat(articles).isNotNull().hasSize(100);
    }
}