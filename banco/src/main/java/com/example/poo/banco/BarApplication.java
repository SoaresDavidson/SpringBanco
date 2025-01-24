package com.example.poo.banco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarApplication.class, args);
    }

    @RestController
    @RequestMapping("/api")
    public static class HelloController {

        @GetMapping("/welcome")
        public String welcome() {
            return "Welcome to Banco Application!";
        }
    }
}
