package de.evangeliumstaucher.app.config;

import de.evangeliumstaucher.app.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class SecurityChainConfig {

    public static final String LOGIN_URL = "/signup";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String USERNAME = "username";
    private static final String DEFAULT_SUCCESS_URL = "/";
    private final UserService sessionService;
    private final AccountConfig accountConfig;

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(r ->
                r
                        .requestMatchers(HttpMethod.GET, "/quiz/pier/")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/quiz/datatable/**")
                        .permitAll()
                        .requestMatchers("/quiz/create/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET, "/quiz/*/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/quiz/*/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/quiz/**")
                        .authenticated()
                        .requestMatchers("/createuser/*")
                        .authenticated()
                        //Pier and game-overview
                        .anyRequest()
                        .permitAll());

        http.csrf(c -> {
            c.csrfTokenRepository(csrfTokenRepository());
        });
        http.oauth2Login(oauth2Login -> oauth2Login.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
                super.onAuthenticationSuccess(request, response, authentication);
                if (!accountConfig.isValid(authentication)) {
                    authentication.setAuthenticated(false);
                }
            }
        }));

        http.exceptionHandling(e -> e.accessDeniedHandler((request, response, accessDeniedException) -> {
                    String redirectUrl = "/error/403"; // Custom error page for 403 Forbidden
                    response.sendRedirect(redirectUrl);
                }
        ));

        return http.build();
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> loggingFilter(UserService userService) {
        FilterRegistrationBean<AuthorizationFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter(userService));
        registrationBean.addUrlPatterns("/quiz/*");
        registrationBean.setOrder(Integer.MAX_VALUE);

        return registrationBean;
    }
}