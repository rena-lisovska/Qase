package api.models.project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateProjectResult {

    @JsonProperty("code")
    public String code;
}
