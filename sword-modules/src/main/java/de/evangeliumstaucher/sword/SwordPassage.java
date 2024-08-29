package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Passage;
import lombok.Data;

@Data
public class SwordPassage implements Passage {
    private String content;

    private String id;
}
