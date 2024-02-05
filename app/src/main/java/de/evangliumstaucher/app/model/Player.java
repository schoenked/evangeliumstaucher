package de.evangliumstaucher.app.model;

import de.evangeliumstaucher.entity.PlayerEntity;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.evangeliumstaucher.entity.PlayerEntity}
 */
@Value
public class Player implements Serializable {
    Long id;
    String sessionId;
    String name;

    public static Player from(PlayerEntity e) {
        return new Player(e.getId(),e.getSessionId(), e.getName());
    }
}