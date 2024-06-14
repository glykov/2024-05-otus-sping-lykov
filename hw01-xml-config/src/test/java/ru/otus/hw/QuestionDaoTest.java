package ru.otus.hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.exceptions.QuestionReadException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test-context.xml")
public class QuestionDaoTest {
    @Autowired
    TestFileNameProvider fileNameProvider;

    @Autowired
    QuestionDao questionDao;

    @Test
    public void testQuestionDao() {
        assertNotNull(questionDao);

       assertThrows(QuestionReadException.class, () -> {
           questionDao.findAll();
       });
    }
}
