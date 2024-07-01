package ru.otus.hw;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.service.TestRunnerService;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Application.class);
        var testRunner = context.getBean(TestRunnerService.class);
        testRunner.run();
    }
}