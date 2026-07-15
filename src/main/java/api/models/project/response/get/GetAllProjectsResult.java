package api.models.project.response.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class GetAllProjectsResult {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("filtered")
    private Integer filtered;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("entities")
    private List<ProjectEntity> entities;
}
