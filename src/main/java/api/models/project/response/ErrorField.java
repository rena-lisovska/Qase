package api.models.project.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorField {

    @JsonProperty("field")
    private String field;

    @JsonProperty("error")
    private String error;
}
