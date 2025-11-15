package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PlayerRepository playerRepository;
    private final AccountConfig accountConfig;

    @Cacheable("player")
    public Optional<Player> get(String name) {
        return playerRepository.findByUsername(name)
                .map(Player::from)
                .stream().findFirst();
    }

    public void create(Player player) {
        playerRepository.save(player.getEntity());
    }

    public void save(PlayerEntity player) {
        playerRepository.save(player);
    }

    public boolean valid(String name) throws AuthenticationException {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return !playerRepository.existsPlayerEntityByUsername(name);
    }

    public boolean exists(String name) {
        return playerRepository.existsPlayerEntityByUsername(name);
    }

    public Optional<PlayerEntity> getByEMail(String email) {
        return playerRepository.findByEmail(email);
    }

    public PlayerModel getPlayerModel(OAuth2User oidcUser, UserService userService) {
        if (oidcUser != null) {
            Optional<PlayerEntity> entity = userService.getByEMail(oidcUser.getAttribute("email"));
            if (entity.isPresent()) {
                return PlayerModel.from(entity.get());
            }
        }
        return null;
    }

}
