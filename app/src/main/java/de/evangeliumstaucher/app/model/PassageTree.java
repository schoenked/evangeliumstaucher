package de.evangeliumstaucher.app.model;

import com.google.gson.Gson;
import de.evangeliumstaucher.app.viewmodel.BaseModel;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PassageTree extends BaseModel {
    public static final Gson gson = new Gson();
    @Getter
    @Setter
    @Nullable
    private final List<PassageTree> passageTrees;
    private String displayText;

    public PassageTree(String id, List<PassageTree> passageTrees) {
        super();
        this.id = id;
        this.passageTrees = passageTrees;
    }

    public PassageTree(String name, String displayText, List<PassageTree> passageTrees) {
        this(name, passageTrees);
        this.displayText = displayText;
    }

    public String getDisplayText() {
        if (displayText == null) {
            return id;
        }
        return displayText;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getJson() {
        return gson.toJson(this);
    }
}
