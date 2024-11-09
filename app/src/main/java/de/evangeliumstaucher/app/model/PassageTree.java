package de.evangeliumstaucher.app.model;

import java.util.List;

public class PassageTree {
    private final List<PassageTree> passageTrees;

    public PassageTree(String id, List<PassageTree> passageTrees) {
        super();

        this.passageTrees = passageTrees;
    }
    public List<PassageTree> getPassageTrees() {
        return passageTrees;
    }
}
