package api.models.project;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

    @Data
    public class GetAllProjectsResult {

        @Expose
        private Integer total;

        @Expose
        private Integer filtered;

        @Expose
        private Integer count;

        @Expose
        private List<ProjectEntity> entities;
    }
