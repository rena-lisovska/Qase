package api.models.project.response.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorField {

    @JsonProperty("field")
    private String field;

    @JsonProperty("error")
    private String error;
}
