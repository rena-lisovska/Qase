package api.models.cases.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ItemCase {
    @JsonProperty("title")
    private String title;

    @JsonProperty("values")
    private List<String> values;
}
