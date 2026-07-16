package api.models.cases.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCaseRequest {
    @JsonProperty("steps_type")
    private String stepsType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("preconditions")
    private String preconditions;

    @JsonProperty("postconditions")
    private String postconditions;

    @JsonProperty("title")
    private String title;

    @JsonProperty("severity")
    private Integer severity;

    @JsonProperty("priority")
    private Integer priority;

    @JsonProperty("behavior")
    private Integer behavior;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("layer")
    private Integer layer;

    @JsonProperty("is_flaky")
    private Integer isFlaky;

    @JsonProperty("suite_id")
    private Integer suiteId;

    @JsonProperty("milestone_id")
    private Integer milestoneId;

    @JsonProperty("isManual")
    private Boolean isManual;

    @JsonProperty("isToBeAutomated")
    private Boolean isToBeAutomated;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("attachments")
    private List<String> attachments;

    @JsonProperty("steps")
    private List<StepCase> steps;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("parameters")
    private List<ParameterCase> parameters;
}
