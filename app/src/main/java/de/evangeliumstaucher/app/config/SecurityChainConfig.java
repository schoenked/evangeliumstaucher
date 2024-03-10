package de.evangeliumstaucher.app.config;

import de.evangeliumstaucher.app.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityChainConfig {

    public static final String LOGIN_URL = "/signup";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String USERNAME = "username";
    private static final String DEFAULT_SUCCESS_URL = "/";
    private final SessionService sessionService;
    private final AccountConfig accountConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(r ->
                        r.requestMatchers("/quiz/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll())
                .oauth2Login(oauth2Login -> oauth2Login.successHandler((request, response, authentication) ->
                {
                    if (!accountConfig.isValid(authentication)) {
                        authentication.setAuthenticated(false);
                    }
                }))
                .exceptionHandling(e -> e.accessDeniedHandler((request, response, accessDeniedException) -> {
                    String redirectUrl = "/error/403"; // Custom error page for 403 Forbidden
                    response.sendRedirect(redirectUrl);
                        }
                ))
        ;

        return http.build();
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/quiz/**");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}