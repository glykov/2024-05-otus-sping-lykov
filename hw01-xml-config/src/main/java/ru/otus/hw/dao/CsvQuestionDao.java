package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        // Использовать CsvToBean
        // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
        // Использовать QuestionReadException
        // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<QuestionDto> result;

        try (Reader reader =
                     new InputStreamReader(
                             ClassLoader.getSystemResourceAsStream(fileNameProvider.getTestFileName()))) {
            Objects.requireNonNull(reader);
            CsvToBean<QuestionDto> cb = new CsvToBeanBuilder<QuestionDto>(reader)
                    .withType(QuestionDto.class)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .build();
            result = cb.parse();

        } catch (Exception e) {
            throw new QuestionReadException(e.getMessage(), e.getCause());
        }

        return result.stream()
                .map(dto -> new Question(dto.getText(), dto.getAnswers()))
                .toList();
    }
}
