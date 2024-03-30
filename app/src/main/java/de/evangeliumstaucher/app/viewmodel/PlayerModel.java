package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.entity.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerModel {
    Long id;
    String name;

    public static PlayerModel from(PlayerEntity playerEntity) {
        return new PlayerModel(playerEntity.getId(), playerEntity.getUsername());
    }
}
