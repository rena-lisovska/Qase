package api.models.project.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProjectResponse {

    @JsonProperty("status")
    public Boolean status;

    @JsonProperty("result")
    public CreateProjectResult result;
}
