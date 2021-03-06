package ru.otus.csv;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.csv.domain.Person;
import ru.otus.csv.service.TestingService;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        TestingService testingService = context.getBean(TestingService.class);

        Person student = testingService.begin();
        System.out.println("Student: " + student.getName() + " " + student.getSurname());
        System.out.println("Result: " + student.getScore());

    }
}
