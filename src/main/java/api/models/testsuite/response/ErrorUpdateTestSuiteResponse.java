package api.models.testsuite.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorUpdateTestSuiteResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("errorFields")
    private List<ErrorField> errorFields;
}
