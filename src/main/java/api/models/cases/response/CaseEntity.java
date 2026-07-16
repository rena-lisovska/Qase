package api.models.cases.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseEntity {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private Object description;

    @JsonProperty("preconditions")
    private Object preconditions;

    @JsonProperty("postconditions")
    private Object postconditions;

    @JsonProperty("severity")
    private Integer severity;

    @JsonProperty("priority")
    private Integer priority;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("layer")
    private Integer layer;

    @JsonProperty("is_flaky")
    private Integer isFlaky;

    @JsonProperty("is_muted")
    private Boolean isMuted;

    @JsonProperty("behavior")
    private Integer behavior;

    @JsonProperty("automation")
    private Integer automation;

    @JsonProperty("isManual")
    private Boolean isManual;

    @JsonProperty("isToBeAutomated")
    private Boolean isToBeAutomated;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("milestone_id")
    private Object milestoneId;

    @JsonProperty("suite_id")
    private Object suiteId;

    @JsonProperty("links")
    private List<Object> links;

    @JsonProperty("custom_fields")
    private List<Object> customFields;

    @JsonProperty("attachments")
    private List<Object> attachments;

    @JsonProperty("steps_type")
    private Object stepsType;

    @JsonProperty("steps")
    private List<Object> steps;

    @JsonProperty("params")
    private List<Object> params;

    @JsonProperty("parameters")
    private List<Object> parameters;

    @JsonProperty("member_id")
    private Integer memberId;

    @JsonProperty("author_id")
    private Integer authorId;

    @JsonProperty("author_uuid")
    private String authorUuid;

    @JsonProperty("tags")
    private List<Object> tags;

    @JsonProperty("deleted")
    private Object deleted;

    @JsonProperty("created")
    private String created;

    @JsonProperty("updated")
    private String updated;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}
