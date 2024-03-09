package de.evangeliumstaucher.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@Primary
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public EmailRejectionFilter emailRejectionFilter(AuthenticationManager authenticationManager, AccountConfig accountConfig) throws Exception {
        EmailRejectionFilter filter = new EmailRejectionFilter(accountConfig);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
