package api.models.project.response.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetAllProjectsResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("result")
    private GetAllProjectsResult result;
}
