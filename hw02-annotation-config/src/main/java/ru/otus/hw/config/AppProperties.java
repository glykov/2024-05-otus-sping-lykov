package ru.otus.hw.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppProperties implements TestConfig, TestFileNameProvider {
    // внедрить свойство из application.properties
    private final int rightAnswersCountToPass;
    // внедрить свойство из application.properties
    private final String testFileName;

    public AppProperties(@Value("${test.fileName}") String testFileName,
                         @Value("${test.rightAnswersCountToPass}") int rightAnswersCountToPass) {
        this.testFileName = testFileName;
        this.rightAnswersCountToPass = rightAnswersCountToPass;
    }
}
