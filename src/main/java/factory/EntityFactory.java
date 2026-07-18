package factory;

import api.adapters.ProjectAdapter;
import api.models.project.request.CreateProjectRequest;
import api.models.project.response.CreateProjectResponse;

public class EntityFactory {
    public static String createProjectCode() {
        CreateProjectRequest request = ProjectFactory.validProjectRq();
        CreateProjectResponse response = ProjectAdapter.createProject(request);
        return response.getResult().getCode();
    }
}
