package api.models.project;

import com.google.gson.annotations.Expose;
import lombok.Data;

    @Data
    public class GetAllProjectsResponse {

        @Expose
        private Boolean status;

        @Expose
        private GetAllProjectsResult result;
    }

