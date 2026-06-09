package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentSerializer {
    private static final Logger logger = LoggerFactory.getLogger(StudentSerializer.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(Student student) {
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                         .writeValueAsString(student);
        } catch (Exception e) {
            logger.error("Ошибка сериализации: {}", e.getMessage());
            return null;
        }
    }

    public Student fromJson(String json) {
        try {
            return mapper.readValue(json, Student.class);
        } catch (Exception e) {
            logger.error("Ошибка десериализации: {}", e.getMessage());
            return null;
        }
    }
}