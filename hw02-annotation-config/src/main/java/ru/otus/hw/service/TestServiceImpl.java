package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final IOService ioService;
    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        TestResult result = new TestResult(student);
        for (var question : questionDao.findAll()) {
            ioService.printLine(question.text());
            int counter = 1;
            for (var answer : question.answers()) {
                ioService.printFormattedLine("\t%d. %s", counter++, answer.text());
            }
            int answerNumber = ioService.readIntForRangeWithPrompt(1, question.answers().size(),
                    "Enter the number of correct answer",
                    "Entered number is out of range");
            result.applyAnswer(question, question.answers().get(answerNumber - 1).isCorrect());
        }
        return result;
    }
}
