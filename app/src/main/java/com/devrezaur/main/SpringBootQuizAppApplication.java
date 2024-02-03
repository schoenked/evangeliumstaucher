package com.devrezaur.main;

import de.evangeliumstaucher.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        // Set cache control to never expire
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                // Set cache control to never expire
                Date now = new Date();
                Date maxAge = new Date(now.getTime() + Long.MAX_VALUE);
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(0, TimeUnit.SECONDS)
                        .maxStale(365, TimeUnit.DAYS)
                        .build();

                return response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        });
        return builder.build();
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
