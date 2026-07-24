package core.factory.api;

import api.adapters.ProjectAdapter;
import api.models.project.request.CreateProjectRequest;
import api.models.project.response.CreateProjectResponse;

public class ApiEntityFactory {
    public static String createProjectCode() {
        CreateProjectRequest request = ApiProjectFactory.validProjectRq();
        CreateProjectResponse response = ProjectAdapter.createProject(request);
        return response.getResult().getCode();
    }
}
