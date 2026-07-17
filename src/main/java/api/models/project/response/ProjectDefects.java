package api.models.project.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDefects {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("open")
    private Integer open;
}
