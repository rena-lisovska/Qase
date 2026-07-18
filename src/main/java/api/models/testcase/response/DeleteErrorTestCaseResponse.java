package api.models.testcase.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteErrorTestCaseResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("errorMessage")
    private String errorMessage;
}
