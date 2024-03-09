package de.evangeliumstaucher.app.config;

import de.evangeliumstaucher.app.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class SecurityChainConfig {

    public static final String LOGIN_URL = "/signup";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String USERNAME = "username";
    private static final String DEFAULT_SUCCESS_URL = "/";
    private final SessionService sessionService;
    private final EmailRejectionFilter emailRejectionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Set<String> googleScopes = new HashSet<>();
        googleScopes.add("https://www.googleapis.com/auth/userinfo.email");

        OidcUserService googleUserService = new OidcUserService();
        googleUserService.setAccessibleScopes(googleScopes);

        http.authorizeHttpRequests(r ->
                        r.requestMatchers("/quiz/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll())
                .oauth2Login(oauthLogin -> {
                            oauthLogin.userInfoEndpoint(userInfoEndpointConfig ->
                                            userInfoEndpointConfig.oidcUserService(googleUserService))
                                    .successHandler(new RefererRedirectionAuthenticationSuccessHandler());
                        }
                )
                .formLogin(i -> {
                    i.successForwardUrl("/createuser");
                })
                .addFilterAfter(emailRejectionFilter, OAuth2LoginAuthenticationFilter.class)
        ;

        return http.build();
    }

}