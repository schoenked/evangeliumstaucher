package com.devrezaur.main;

import de.evangeliumstaucher.BiblesApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.query.Param;

@SpringBootApplication
public class SpringBootQuizAppApplication {

    @Value("${bibleapi.apikey}")
    private String token;

    @Bean
    public BiblesApi biblesApi() {
        BiblesApi api = new BiblesApi();
        api.getApiClient().setApiKey(token);
        return api;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuizAppApplication.class, args);
    }

}
