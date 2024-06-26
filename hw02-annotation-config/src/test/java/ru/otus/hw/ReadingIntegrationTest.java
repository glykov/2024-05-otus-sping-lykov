package ru.otus.hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ReadingIntegrationTest.class})
@Configuration
@ComponentScan
@TestPropertySource("classpath:test.properties")
public class ReadingIntegrationTest {
    @Autowired
    AppProperties properties;

    @Autowired
    QuestionDao questionDao;

    @Test
    public void testReadingQuestions() {
        List<Question> result = questionDao.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(6);
        assertThat(result.get(0).text()).isEqualTo("Is this test question?");
        assertThat(result.get(0).answers().get(0).isCorrect()).isTrue();
        assertThat(result.get(0).answers().get(1).isCorrect()).isFalse();
    }
}
