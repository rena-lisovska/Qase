package api.models.testsuite.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CRUDTestSuiteResponse {

        @JsonProperty("status")
        private Boolean status;

        @JsonProperty("result")
        private CRUDTestSuiteResult result;
}
