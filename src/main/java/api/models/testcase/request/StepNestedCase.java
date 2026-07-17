package api.models.testcase.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StepNestedCase {
    @JsonProperty("newKey")
    private String newKey;

    @JsonProperty("newKey-1")
    private String newKey1;
}
