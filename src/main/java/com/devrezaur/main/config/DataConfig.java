package com.devrezaur.main.config;

import com.devrezaur.main.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.komamitsu.spring.data.sqlite.EnableSqliteRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableSqliteRepositories("com.devrezaur.main")
@EnableJpaRepositories
@Configuration
@RequiredArgsConstructor
public class DataConfig {

    private final QuestionRepository questionRepository;

}
