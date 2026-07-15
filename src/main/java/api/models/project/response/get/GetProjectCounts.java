package api.models.project.response.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetProjectCounts {
    @JsonProperty("cases")
    private Integer cases;

    @JsonProperty("suites")
    private Integer suites;

    @JsonProperty("milestones")
    private Integer milestones;

    @JsonProperty("runs")
    private GetProjectRuns runs;

    @JsonProperty("defects")
    private GetProjectDefects defects;
}
