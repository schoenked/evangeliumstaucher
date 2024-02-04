package de.evangeliumstaucher.config;

import de.evangeliumstaucher.repo.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@RequiredArgsConstructor
public class DataConfig {

    //private final GameRepository gameRepository;

}
