package ru.otus.hw;

import org.junit.jupiter.api.Test;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.StreamsIOService;
import ru.otus.hw.service.StudentService;
import ru.otus.hw.service.StudentServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceImplTest {

    @Test
    public void testStudentService() {
        IOService ioService = mock(StreamsIOService.class);
        StudentService studentService = new StudentServiceImpl(ioService);

        when(ioService.readStringWithPrompt("Please input your first name"))
                .thenReturn("Ivan");
        when(ioService.readStringWithPrompt("Please input your last name"))
                .thenReturn("Ivanov");
        assertThat(studentService.determineCurrentStudent().getFullName())
                .isEqualTo("Ivan Ivanov");
    }
}
