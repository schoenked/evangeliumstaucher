package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.AuthenticatedPrincipal;
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

    public Optional<PlayerEntity> getByGlobalId(String globalId) {
        return playerRepository.findByGlobalId(globalId);
    }

    public PlayerModel getPlayerModel(AuthenticatedPrincipal principal, UserService userService) {
        String globalId = getGlobalId(principal);

        if (globalId != null) {
            Optional<PlayerEntity> entity = userService.getByGlobalId(globalId);
            if (entity.isPresent()) {
                return PlayerModel.from(entity.get());
            }
        }

        return null;
    }

    public String getGlobalId(AuthenticatedPrincipal principal) {
        String globalId = null;

        if (principal != null && principal instanceof OAuth2User oAuth2User) {
            globalId = oAuth2User.getAttribute("email");
        } else if (principal != null && principal instanceof AuthenticatedPrincipal authenticatedPrincipal) {
            globalId = authenticatedPrincipal.getName();
        }
        return  globalId;
    }
}
