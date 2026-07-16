package api.models.cases.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class StepCase {
    @JsonProperty("action")
    private String action;

    @JsonProperty("expected_result")
    private String expectedResult;

    @JsonProperty("data")
    private String data;

    @JsonProperty("value")
    private String value;

    @JsonProperty("attachments")
    private List<String> attachments;

    @JsonProperty("steps")
    private List<StepNestedCase> steps;
}
