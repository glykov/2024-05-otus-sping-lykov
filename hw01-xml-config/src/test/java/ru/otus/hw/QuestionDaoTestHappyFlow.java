package ru.otus.hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test-context-2.xml")
public class QuestionDaoTestHappyFlow {
    @Autowired
    TestFileNameProvider fileNameProvider;

    @Autowired
    QuestionDao questionDao;

    @Test
    public void testQuestionDao() {
        assertNotNull(questionDao);
        List<Question> list = questionDao.findAll();

        assertNotNull(list);
        assertEquals(5, list.size());

        assertEquals("Is there life on Mars?", list.get(0).text());
        assertEquals("Science doesn't know this yet", list.get(0).answers().get(0).text());
        assertTrue(list.get(0).answers().get(0).isCorrect());
        assertFalse(list.get(0).answers().get(1).isCorrect());
    }
}
