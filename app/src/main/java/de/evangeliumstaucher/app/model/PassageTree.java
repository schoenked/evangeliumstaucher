package de.evangeliumstaucher.app.model;

import com.google.gson.Gson;
import de.evangeliumstaucher.app.viewmodel.BaseModel;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@With
@SuperBuilder
public class PassageTree extends BaseModel {
    public static final Gson gson = new Gson();

    @Nullable
    private final List<PassageTree> passageTrees;

    private String displayText;
    private boolean collapsed = true;
    private boolean activeWhitelist = false;
    private boolean activeBlacklist = false;

    public static PassageTreeBuilder<?, ?> builder() {
        return new PassageTreeBuilderImpl().collapsed(true);
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
