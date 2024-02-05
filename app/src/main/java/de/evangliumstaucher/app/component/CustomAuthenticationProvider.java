package de.evangliumstaucher.app.component;

import de.evangliumstaucher.app.model.Player;
import de.evangliumstaucher.app.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final SessionService sessionService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getPrincipal().toString();
        if (!sessionService.valid(name)) {
            throw new AuthenticationException("name already exists") {
            };
        }
        // Create a fully authenticated Authentication object
        UserDetails userDetails = User.withUsername(name)
                .password("")
                .roles("USER")
                .build();
        String sessionId = ((WebAuthenticationDetails) ((UsernamePasswordAuthenticationToken) authentication).getDetails()).getSessionId();
        sessionService.create(new Player(null, sessionId, name));
        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities());
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Return true if this AuthenticationProvider supports the provided authentication class
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}