package api.models.testsuite.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorGetSpecificTestSuiteResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("errorMessage")
    private String errorMessage;
}
