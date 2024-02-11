package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final PlayerRepository playerRepository;
    private final AccountConfig accountConfig;

    @Cacheable("player")
    public Optional<Player> get(String name) {
        return playerRepository.findByName(name)
                .map(Player::from)
                .stream().findFirst();
    }

    public void create(Player player) {
        playerRepository.save(player.getEntity());
    }

    public boolean valid(String name) throws AuthenticationException {
        if (StringUtils.isEmpty(name)) {
            throw new AuthenticationServiceException("Name nicht erlaubt.");
        }
        if (playerRepository.existsPlayerEntityByName(name)) {
            throw new AuthenticationServiceException("Name ist nicht mehr verfügbar.");
        }
        if (accountConfig.getWhitelist() != null && !accountConfig.getWhitelist().isEmpty() && !accountConfig.getWhitelist().contains(name)) {
            throw new AuthenticationServiceException("Kenn ich nicht.");
        }
        return true;
    }
}
