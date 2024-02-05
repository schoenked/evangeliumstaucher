package de.evangliumstaucher.app.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authManager(AuthenticationProvider authProvider) {
        return new ProviderManager(authProvider);
    }

    @Bean
    public AuthenticationProvider authProvider() {
        return new CustomAuthenticationProvider();
    }
}