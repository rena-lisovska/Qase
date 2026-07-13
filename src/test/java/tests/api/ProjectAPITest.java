package tests.api;

import api.adapters.ProjectAdapter;
import api.models.project.CreateProjectRequest;
import api.models.project.CreateProjectResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class ProjectAPITest {

    private final String CODE = "QA1";

    @Test
    public void checkCreateProject() {
        CreateProjectRequest request = CreateProjectRequest.builder()
                .title("QA02")
                .code(CODE)
                .description("New project")
                .access("all")
                .group("all")
                .build();
        CreateProjectResponse response = ProjectAdapter.сreateProject(request);
        assertTrue(response.status);
        assertEquals(response.result.code, "QA1");
    }

    @Test
    public void getAllProjects(){
        ProjectAdapter.getAllProjects();
    }

    @Test
    public void deleteProject() {
        ProjectAdapter.deleteProject(CODE);
    }
}
