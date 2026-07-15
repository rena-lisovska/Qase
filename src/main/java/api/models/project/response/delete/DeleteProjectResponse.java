package api.models.project.response.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteProjectResponse {

    @JsonProperty("status")
    private Boolean status;
}
