package api.models.project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateProjectResponse {

    @JsonProperty("status")
    public Boolean status;

    @JsonProperty("result")
    public CreateProjectResult result;
}
