package de.evangeliumstaucher.app.viewmodel;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseModel {

    @NotEmpty
    public String id;

    public String getHtmlIdEscaped() {
        String outreturn = StringUtils.replace(getHtmlId(), ".", "\\.");
        outreturn = StringUtils.replace(outreturn, "(", "\\(");
        outreturn = StringUtils.replace(outreturn, ")", "\\)");
        return outreturn;
    }

    public String getHtmlId() {
        if (id == null) {
            return null;
        }
        String outreturn = StringUtils.left(id, 1)
                .replace("1", "first")
                .replace("2", "second")
                .replace("3", "third")
                .replace("4", "fourth")
                .replace("5", "fifth")
                + StringUtils.substring(id, 1);
        outreturn = StringUtils.deleteWhitespace(outreturn);

        return outreturn;
    }
}
