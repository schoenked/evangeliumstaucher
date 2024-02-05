package de.evangliumstaucher.app.component;

import de.evangliumstaucher.app.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final SessionService sessionService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getPrincipal().toString();
        if (sessionService.valid(name)) {
            throw new AuthenticationException("name already exists") {
            };
        }
        // Create a fully authenticated Authentication object
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(name)
                .password("")
                .roles("USER")
                .build();
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