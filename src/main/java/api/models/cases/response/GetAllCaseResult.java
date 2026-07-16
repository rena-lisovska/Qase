package api.models.cases.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllCaseResult {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("filtered")
    private Integer filtered;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("entities")
    private List<CaseEntity> entities;
}
