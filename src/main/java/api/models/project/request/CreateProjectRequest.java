package api.models.project.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("access")
    private String access;

    @JsonProperty("group")
    private String group;
}
