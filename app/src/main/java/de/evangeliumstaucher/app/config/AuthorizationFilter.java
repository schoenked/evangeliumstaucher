package de.evangeliumstaucher.app.config;

import de.evangeliumstaucher.app.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

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
            Principal userPrincipal = req.getUserPrincipal();
            String globalId = null;
            if (userPrincipal instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken && oAuth2AuthenticationToken.getPrincipal() != null) {
                DefaultOAuth2User oidcUser = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
                if (oidcUser != null) {
                    globalId = (String) oidcUser.getAttributes().get("email");
                }
            } else if (userPrincipal instanceof Authentication principal
                    &&  principal.getPrincipal() instanceof  AuthenticatedPrincipal authenticatedPrincipal) {
                globalId = authenticatedPrincipal.getName();
            }
            if (globalId != null) {
                return userService.getByGlobalId(globalId).isPresent();
            }
        }
        return true;
    }


}
