package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.entity.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.evangeliumstaucher.entity.PlayerEntity}
 */
@Value
@AllArgsConstructor
public class Player implements Serializable {
    Long id;
    String name;

    public static Player from(PlayerEntity e) {
        return new Player(e.getId(), e.getName());
    }

    public PlayerEntity getEntity() {
        return new PlayerEntity(null, name);
    }
}