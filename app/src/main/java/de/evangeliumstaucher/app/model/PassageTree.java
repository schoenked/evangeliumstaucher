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
    @Getter
    @Setter
    @Nullable
    private final List<PassageTree> passageTrees;
    public static final Gson gson = new Gson();

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getId() {
        return id;
    }

    public PassageTree(String id, List<PassageTree> passageTrees) {
        super();
        this.id = id;
        this.passageTrees = passageTrees;
    }

    public String getJson() {
        return gson.toJson(this);
    }
}
