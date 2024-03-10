package de.evangeliumstaucher.app.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.List;

@Configuration
@ConfigurationProperties("accounts")
@Getter
@RequiredArgsConstructor
public class AccountConfig {
    private final List<String> whitelist;

    public boolean isValid(Authentication authentication) {
        if (whitelist == null || whitelist.isEmpty()) {
            return true;
        }

        String email = ((DefaultOidcUser) ((OAuth2AuthenticationToken) authentication).getPrincipal()).getIdToken().getClaims().get("email").toString();

        return whitelist.contains(email);
    }
}
