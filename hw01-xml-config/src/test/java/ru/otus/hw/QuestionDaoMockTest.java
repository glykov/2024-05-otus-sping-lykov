package ru.otus.hw;

import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionDaoMockTest {

    @Test
    public void testQuestionDao() {
        TestFileNameProvider provider = mock(AppProperties.class);
        QuestionDao questionDao = new CsvQuestionDao(provider);

        when(provider.getTestFileName()).thenReturn("test-questions.csv");
        assertThat(questionDao).isNotNull();
        assertThat(questionDao.findAll()).isNotNull();
        assertThat(questionDao.findAll().size()).isEqualTo(5);
    }
}
