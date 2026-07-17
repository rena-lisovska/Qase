package api.models.testsuite.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUpdateTestSuiteRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("preconditions")
    private String preconditions;

    @JsonProperty("parent_id")
    private Integer parentId;
}
