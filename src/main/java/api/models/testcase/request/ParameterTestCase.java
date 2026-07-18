package api.models.testcase.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ParameterTestCase {
    @JsonProperty("title")
    private String title;

    @JsonProperty("values")
    private List<String> values;

    @JsonProperty("items")
    private List<ItemTestCase> items;
}
