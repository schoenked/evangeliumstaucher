package de.evangeliumstaucher.app.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("accounts")
@Getter
@RequiredArgsConstructor
public class AccountConfig {
    private final List<String> whitelist;
}
