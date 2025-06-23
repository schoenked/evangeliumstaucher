package de.evangeliumstaucher.app.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import java.io.IOException;

public class EmailRejectionFilter extends AbstractAuthenticationProcessingFilter {

    private final AccountConfig accountConfig;

    public EmailRejectionFilter(AccountConfig accountConfig) throws Exception {
        super(PathPatternRequestMatcher.withDefaults().matcher("/login/oauth2/code/*"));
        this.accountConfig = accountConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        if (accountConfig.getWhitelist() != null) {
            if (!accountConfig.getWhitelist().contains(email)) {
                throw new RuntimeException("Du bist noch kein Testbenutzer");
            }
        }
        // Continue with the authentication process
        return null;
    }

}