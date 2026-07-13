package api.models.project;

import com.google.gson.annotations.Expose;
import lombok.Data;

    @Data
    public class ProjectEntity {

        @Expose
        private String title;

        @Expose
        private String code;
    }

