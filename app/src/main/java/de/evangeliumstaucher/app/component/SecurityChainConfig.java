package de.evangeliumstaucher.app.component;

import de.evangeliumstaucher.app.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityChainConfig {

    public static final String LOGIN_URL = "/signup";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error=true";
    public static final String USERNAME = "username";
    private static final String DEFAULT_SUCCESS_URL = "/";
    private final SessionService sessionService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.authorizeHttpRequests(r -> {
                    r.requestMatchers("/quiz/**")
                            .authenticated()
                            .anyRequest()
                            .permitAll();
                })
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .loginProcessingUrl("/createuser")
                        .failureUrl(LOGIN_FAIL_URL)
                        .usernameParameter(USERNAME)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL))
                .authenticationManager(authenticationManager)
                /*.authenticationManager(authentication -> {
                    authentication.getCredentials();
                    authentication.
                    return authentication;
                })*/;

        return http.build();
    }

}