package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.app.viewmodel.BaseModel;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class PassageTree extends BaseModel {
    @Getter
    @Nullable
    private final List<PassageTree> passageTrees;

    public String getId() {
        return id;
    }

    public PassageTree(String id, List<PassageTree> passageTrees) {
        super();
        this.id = id;
        this.passageTrees = passageTrees;
    }
}
