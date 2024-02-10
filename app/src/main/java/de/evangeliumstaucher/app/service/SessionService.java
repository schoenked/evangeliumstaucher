package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final PlayerRepository playerRepository;

    @Cacheable("player")
    public Optional<Player> get(String sessionId) {
        return playerRepository.findBySessionId(sessionId)
                .map(Player::from)
                .stream().findFirst();
    }

    public void create(Player player) {
        playerRepository.save(player.getEntity());
    }

    public boolean valid(String name) {
        return !playerRepository.existsPlayerEntityByName(name);
    }
}
