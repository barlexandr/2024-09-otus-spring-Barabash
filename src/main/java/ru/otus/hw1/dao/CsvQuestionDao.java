package ru.otus.hw1.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw1.config.TestFileNameProvider;
import ru.otus.hw1.dao.dto.QuestionDto;
import ru.otus.hw1.domain.Question;
import ru.otus.hw1.exceptions.QuestionReadException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        try (var fileReader = new FileReader(fileNameProvider.getTestFileName())) {
            var csvToBean = new CsvToBeanBuilder<QuestionDto>(fileReader)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .withType(QuestionDto.class)
                    .build();
            var questionDtos = csvToBean.parse();
            return questionDtos.stream().map(QuestionDto::toDomainObject).toList();
        } catch (IOException e) {
            throw new QuestionReadException(
                    String.format("An error occurred while reading data from %s file.",
                            fileNameProvider.getTestFileName())
            );
        }
    }
}
