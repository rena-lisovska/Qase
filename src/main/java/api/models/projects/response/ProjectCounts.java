package api.models.projects.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectCounts {

    @JsonProperty("cases")
    private Integer cases;

    @JsonProperty("suites")
    private Integer suites;

    @JsonProperty("milestones")
    private Integer milestones;

    @JsonProperty("runs")
    private ProjectRuns runs;

    @JsonProperty("defects")
    private ProjectDefects defects;
}
