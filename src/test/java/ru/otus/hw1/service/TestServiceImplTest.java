package ru.otus.hw1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw1.dao.CsvQuestionDao;
import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    private TestService testService;

    @Mock
    private IOService ioService;

    @Mock
    private CsvQuestionDao csvQuestionDao;

    @BeforeEach
    void setUp() {
        this.testService = new TestServiceImpl(
                ioService,
                csvQuestionDao
        );
    }

    @Test
    void executeTest() {
        var answers = List.of(
                new Answer("First answer", false),
                new Answer("Second answer", true)
        );
        var questions = new ArrayList<Question>();
        questions.add(new Question("question?", answers));

        when(csvQuestionDao.findAll())
                .thenReturn(questions);

        assertDoesNotThrow(() -> testService.executeTest());

        verify(csvQuestionDao).findAll();
        verify(ioService, times(2)).printLine(anyString());
    }
}