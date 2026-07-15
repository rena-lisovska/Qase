package api.models.project.response.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetProjectResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("result")
    private GetProjectResult result;
}
