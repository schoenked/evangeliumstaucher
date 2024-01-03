package com.devrezaur.main;

import de.evangeliumstaucher.*;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringBootQuizAppApplication {

    @Value("${bibleapi.apikey}")
    private String token;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuizAppApplication.class, args);
    }

    @Bean
    public OkHttpClient httpClient() {
        Cache cache = new Cache(new File("cache/"), 1024 * 1024 * 1024);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .callTimeout(60, TimeUnit.SECONDS)
                .build();
        return client;
    }

    @Bean
    public PassagesApi passagesApi(OkHttpClient httpClient) {
        PassagesApi api = new PassagesApi();
        api.getApiClient().setHttpClient(httpClient);
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public BiblesApi biblesApi(OkHttpClient httpClient) {
        BiblesApi api = new BiblesApi();
        api.getApiClient().setHttpClient(httpClient);
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public BooksApi booksApi(OkHttpClient httpClient) {
        BooksApi api = new BooksApi();
        api.getApiClient().setHttpClient(httpClient);
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public ChaptersApi chaptersApi(OkHttpClient httpClient) {
        ChaptersApi api = new ChaptersApi();
        api.getApiClient().setHttpClient(httpClient);
        api.getApiClient().setApiKey(token);
        return api;
    }

    @Bean
    public VersesApi versesApi(OkHttpClient httpClient) {
        VersesApi api = new VersesApi();
        api.getApiClient().setHttpClient(httpClient);
        api.getApiClient().setApiKey(token);
        return api;
    }

}
