package ru.otus.hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.StreamsIOService;
import ru.otus.hw.service.TestService;
import ru.otus.hw.service.TestServiceImpl;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServiceImplTest {
    IOService ioService;
    QuestionDao questionDao;
    TestService testService;
    Student student;
    List<Question> questions;

    @BeforeEach
    public void setUp() {
        student = new Student("Simple", "Student");
        questions = List.of(new Question("Question",
                List.of(new Answer("Answer 1", true), new Answer("Answer 2", false))));
    }

    @Test
    public void testingTestServiceWithCorrectAnswer() {
        ioService = mock(StreamsIOService.class);
        questionDao = mock(CsvQuestionDao.class);
        testService = new TestServiceImpl(ioService, questionDao);

        when(questionDao.findAll()).thenReturn(questions);
        when(ioService.readIntForRangeWithPrompt(1, 2,
                "Enter the number of correct answer", "Entered number is out of range"))
                .thenReturn(1);

        assertThat(testService.executeTestFor(student).getRightAnswerCount()).isEqualTo(1);
        assertThat(testService.executeTestFor(student).getAnsweredQuestions().size()).isEqualTo(1);
        assertThat(testService.executeTestFor(student).getStudent().getFullName()).isEqualTo("Simple Student");
    }

    @Test
    public void testingTestServiceWithWrongAnswer() {
        ioService = mock(StreamsIOService.class);
        questionDao = mock(CsvQuestionDao.class);
        testService = new TestServiceImpl(ioService, questionDao);

        when(questionDao.findAll()).thenReturn(questions);
        when(ioService.readIntForRangeWithPrompt(1, 2,
                "Enter the number of correct answer", "Entered number is out of range"))
                .thenReturn(2);

        assertThat(testService.executeTestFor(student).getRightAnswerCount()).isEqualTo(0);
        assertThat(testService.executeTestFor(student).getAnsweredQuestions().size()).isEqualTo(1);
        assertThat(testService.executeTestFor(student).getStudent().getFullName()).isEqualTo("Simple Student");
    }
}
