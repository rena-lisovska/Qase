package api.models.project.response.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProjectEntity {

    @JsonProperty("title")
    private String title;

    @JsonProperty("code")
    private String code;
}
