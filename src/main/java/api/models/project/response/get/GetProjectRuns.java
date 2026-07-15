package api.models.project.response.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GetProjectRuns {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("active")
    private Integer active;
}
