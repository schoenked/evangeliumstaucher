package de.evangliumstaucher.app.component;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (!"".equals("")) {
            throw new AuthenticationException("Invalid credentials") {};
        }
        // Create a fully authenticated Authentication object
        User userDetails = new User("","", null);
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