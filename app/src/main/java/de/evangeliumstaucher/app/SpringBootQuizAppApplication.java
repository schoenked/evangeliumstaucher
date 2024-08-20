package de.evangeliumstaucher.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "de.evangeliumstaucher.repo")
@EntityScan(basePackages = {"de.evangeliumstaucher.entity"})
@ComponentScan(basePackages = {"de.evangeliumstaucher"})
public class SpringBootQuizAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuizAppApplication.class, args);
    }
}
