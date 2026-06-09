package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Запуск приложения");

        List<Student> students = List.of(
            new Student("Анна", 20, 9.5),
            new Student("Иван", 22, 7.8),
            new Student("Мария", 21, 8.3)
        );

        logger.info("Размер таблицы: {}", students.size());

        StudentSerializer serializer = new StudentSerializer();

        for (Student s : students) {
            String json = serializer.toJson(s);
            logger.info("Сериализован: {}", json);

            Student restored = serializer.fromJson(json);
            logger.info("Десериализован: {}", restored);
        }
    }
}