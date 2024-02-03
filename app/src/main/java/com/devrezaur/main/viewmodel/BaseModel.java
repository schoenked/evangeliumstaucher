package com.devrezaur.main.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.apache.commons.lang3.StringUtils;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {

    public String id;

    public String getHtmlIdEscaped() {
        String outreturn = StringUtils.replace(getHtmlId(), ".", "\\.");
        return outreturn;
    }
    public String getHtmlId() {
        String outreturn = StringUtils.left(id,1)
                .replace("1", "first")
                .replace("2", "second")
                .replace("3", "third")
                .replace("4", "fourth")
                .replace("5", "fifth")
                + StringUtils.substring(id, 1);

        return outreturn;
    }
}
