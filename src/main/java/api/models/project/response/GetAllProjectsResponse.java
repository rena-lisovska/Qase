package api.models.project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetAllProjectsResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("result")
    private GetAllProjectsResult result;
}
