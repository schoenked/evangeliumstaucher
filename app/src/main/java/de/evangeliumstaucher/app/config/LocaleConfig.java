package de.evangeliumstaucher.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(Arrays.asList(
                Locale.GERMANY,
                Locale.GERMAN
        ));
        //todo support english (problem bible keys are locale based stored in db)
        resolver.setDefaultLocale(Locale.GERMANY);
        return resolver;
    }
}
