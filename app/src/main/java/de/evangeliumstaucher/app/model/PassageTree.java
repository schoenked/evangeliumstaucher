package de.evangeliumstaucher.app.model;

import com.google.gson.Gson;
import de.evangeliumstaucher.app.utils.JsonCompressor;
import de.evangeliumstaucher.app.viewmodel.BaseModel;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@With
@SuperBuilder
public class PassageTree extends BaseModel {
    public static final Gson gson = new Gson();

    @Nullable
    private List<PassageTree> tree;

    @Nullable
    public List<PassageTree> getTree() {
        return tree;
    }

    private String displ;
    private boolean coll = true;
    private boolean actWhi = false;
    private boolean actBl = false;

    public static PassageTreeBuilder<?, ?> builder() {
        return new PassageTreeBuilderImpl().coll(true);
    }

    public String getDispl() {
        if (displ == null) {
            return id;
        }
        return displ;
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

    public String getCompressedJson() {
        String rawJson = getJson(); // Deine bestehende JSON-Generierung
        return JsonCompressor.compress(rawJson);
    }
}
