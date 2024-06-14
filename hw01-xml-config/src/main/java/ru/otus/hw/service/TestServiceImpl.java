package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // get tests from DAO and print them
        var questions = questionDao.findAll();
        for (var question : questions) {
            ioService.printLine(question.text());
            int answerNum = 1;
            for (var answer : question.answers()) {
                ioService.printFormattedLine("\t%d. %s", answerNum++, answer.text());
            }
            System.out.println();
        }
    }
}
