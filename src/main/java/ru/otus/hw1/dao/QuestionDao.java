package ru.otus.hw1.dao;

import ru.otus.hw1.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
