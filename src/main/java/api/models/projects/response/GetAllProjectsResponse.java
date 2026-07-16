package api.models.projects.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllProjectsResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("result")
    private GetAllProjectsResult result;
}
