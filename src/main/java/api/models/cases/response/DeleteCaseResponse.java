package api.models.cases.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteCaseResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("result")
    private CRUDCaseResult result;
}
