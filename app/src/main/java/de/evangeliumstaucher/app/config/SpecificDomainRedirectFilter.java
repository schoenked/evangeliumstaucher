package de.evangeliumstaucher.app.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SpecificDomainRedirectFilter extends OncePerRequestFilter {

    private static final String DOMAIN_TO_REDIRECT = "evangeliumstaucher.nobler.tech";
    private static final String TARGET_HOST = "evangeliumstaucher.edel.gesinnt.de";
    private static final String TARGET_SCHEME = "https";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String serverName = request.getServerName();

        // Check if the request comes exactly from the domain you want to redirect
        if (DOMAIN_TO_REDIRECT.equals(serverName)) {

            StringBuilder redirectUrl = new StringBuilder();
            redirectUrl.append(TARGET_SCHEME).append("://").append(TARGET_HOST).append(request.getRequestURI());

            String queryString = request.getQueryString();
            if (queryString != null) {
                redirectUrl.append("?").append(queryString);
            }

            // Set HTTP 301 and Location header
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", redirectUrl.toString());

            // Abort the request chain here
            return;
        }

        // If it's any other domain (e.g., localhost, or the target domain itself), proceed normally
        filterChain.doFilter(request, response);
    }
}