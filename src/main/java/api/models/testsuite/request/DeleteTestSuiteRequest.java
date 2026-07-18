package api.models.testsuite.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteTestSuiteRequest {

    @JsonProperty("destination_id")
    private Integer destinationId;
}
