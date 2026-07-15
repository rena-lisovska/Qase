package api.models.project.response.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ErrorCreateProjectResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("errorFields")
    private List<ErrorField> errorFields;
}
