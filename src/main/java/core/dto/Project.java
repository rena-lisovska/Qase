package core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {

    private String name;
    private String code;
    private String description;
    private String accessType;
    private String memberAccess;
    private String group;
}