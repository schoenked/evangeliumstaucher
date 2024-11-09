package de.evangeliumstaucher.app.model;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class PassageTree {
    @Getter
    @Nullable
    private final List<PassageTree> passageTrees;
    @Getter
    private final String id;

    public PassageTree(String id, List<PassageTree> passageTrees) {
        super();
        this.id = id;
        this.passageTrees = passageTrees;
    }
}
