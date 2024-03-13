package de.evangeliumstaucher.app.config;

import de.evangeliumstaucher.app.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Checks for
 * - whitelisted mail address (if configured)
 * - username set for profile
 */
@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {
    private final UserService userService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!checkUsernameSetup(req)) {
            log.debug("redirecting to createuser");
            res.setStatus(303);
            res.setHeader("Location", "/signup?forwardTo=" + URLEncoder.encode(req.getRequestURI(), StandardCharsets.UTF_8));
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean checkUsernameSetup(HttpServletRequest req) {

        if (req != null && req.getUserPrincipal() != null) {
            OAuth2AuthenticationToken userPrincipal = (OAuth2AuthenticationToken) req.getUserPrincipal();
            if (userPrincipal != null && userPrincipal.getPrincipal() != null) {
                DefaultOAuth2User oidcUser = (DefaultOAuth2User) userPrincipal.getPrincipal();
                if (oidcUser != null) {
                    String mail = (String) oidcUser.getAttributes().get("email");
                    if (mail != null) {
                        return userService.getByEMail(mail).isPresent();
                    }
                }
            }
        }
        return true;
    }


}
