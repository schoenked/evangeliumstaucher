package com.devrezaur.main;

import de.evangeliumstaucher.BiblesApi;
import de.evangeliumstaucher.BooksApi;
import de.evangeliumstaucher.ChaptersApi;
import de.evangeliumstaucher.VersesApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootQuizAppApplication {

    @Value("${bibleapi.apikey}")
    private String token;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuizAppApplication.class, args);
    }

    @Bean
    public BiblesApi biblesApi() {
        BiblesApi api = new BiblesApi();
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public BooksApi booksApi() {
        BooksApi api = new BooksApi();
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public ChaptersApi chaptersApi() {
        ChaptersApi api = new ChaptersApi();
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public VersesApi versesApi() {
        VersesApi api = new VersesApi();
        api.getApiClient().setApiKey(token);
        return api;
    }

}
