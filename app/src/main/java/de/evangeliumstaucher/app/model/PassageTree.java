package de.evangeliumstaucher.app.model;

import java.util.List;

public class PassageTree {
    private final List<PassageTree> passageTrees;
    private final String id;

    public PassageTree(String id, List<PassageTree> passageTrees) {
        super();
        this.id = id;
        this.passageTrees = passageTrees;
    }

    public List<PassageTree> getPassageTrees() {
        return passageTrees;
    }
}
