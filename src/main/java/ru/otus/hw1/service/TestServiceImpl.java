package ru.otus.hw1.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw1.dao.CsvQuestionDao;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final CsvQuestionDao csvQuestionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        csvQuestionDao.findAll()
                .forEach(questions -> {
                    ioService.printFormattedLine(questions.text());
                    questions.answers().forEach(answer -> ioService.printFormattedLine(answer.text()));
                    ioService.printLine("");
                });
    }
}
