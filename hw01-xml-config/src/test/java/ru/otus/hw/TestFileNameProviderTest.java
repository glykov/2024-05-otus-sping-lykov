package ru.otus.hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.config.AppProperties;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test-context.xml")
public class TestFileNameProviderTest {
    @Autowired
    AppProperties provider;

    @Test
    public void testTestFileNameProvider() {
        assertThat(provider).isNotNull();
        assertThat(provider.getTestFileName()).isEqualTo("classpath:questions.csv");
    }
}
