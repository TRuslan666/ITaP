package org.example.controller ;

import org.springframework.web.bind.annotation.GetMapping ;
import org.springframework.web.bind.annotation.RestController ;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {

    @GetMapping ("/hello")
    public String sayHello () {
        return "Привет, Spring Boot!";
    }
     @GetMapping ("/goodbye")
    public String sayGoodbye () {
        return "До свидания, Spring Boot!";
    }
    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "Мир") String name) {
        return String.format("Привет, %s!", name);
    }
    @GetMapping("/greet2")
    public String greet2(
        @RequestParam(value = "name",  defaultValue = "Мир") String name, 
        @RequestParam(value = "age", defaultValue = "0") int age) {
        return String.format("Пользователь: %s, Возраст: %d лет", name, age);
    }
}