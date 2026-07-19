package tests.api;

import api.adapters.ProjectAdapter;
import api.models.project.request.CreateProjectRequest;
import api.models.project.response.CreateProjectResponse;
import api.models.project.response.ErrorCreateProjectResponse;
import api.models.project.response.DeleteProjectResponse;
import api.models.project.response.GetProjectResponse;
import factory.ProjectFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProjectApiTest {

    @Test(
            testName = "Create, get and delete project with valid data",
            description = "Verifies that user can: create project with valid data, get created project, delete project",
            groups = {"regression", "api", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Qase_01")
    @Feature("Project")
    @Story("CRUD operations on project with all fields")
    public void checkCRUDProject() {
        SoftAssert softAssert = new SoftAssert();
        CreateProjectRequest request = ProjectFactory.validProjectRq();
        CreateProjectResponse createResponse = ProjectAdapter.createProject(request);
        softAssert.assertTrue(
                createResponse.getStatus(),
                "The project status is not true");
        String projectCode = createResponse.getResult().getCode();
        GetProjectResponse getResponse = ProjectAdapter.getProject(projectCode);
        softAssert.assertEquals(
                getResponse.getResult().getCode(),
                request.getCode(),
                "Inconsistency of the received code"
        );
        softAssert.assertEquals(
                getResponse.getResult().getTitle(),
                request.getTitle(),
                "Inconsistency of the received title"
        );
        DeleteProjectResponse deleteResponse = ProjectAdapter.deleteProject(projectCode);
        softAssert.assertTrue(
                deleteResponse.getStatus(),
                "Failed to delete project with code: " +projectCode
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Create project with only required fields",
            description = "Verifies that user can create project with only required fields",
            groups = {"smoke", "api", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Qase_01")
    @Feature("Project")
    @Story("Create project with only required fields")
    public void createProjectWithRequiredFields() {
        SoftAssert softAssert = new SoftAssert();
        CreateProjectRequest request = ProjectFactory.minimalProjectRq();
        CreateProjectResponse createResponse = ProjectAdapter.createProject(request);
        softAssert.assertTrue(
                createResponse.getStatus(),
                "The project status is not true");
        String projectCode = createResponse.getResult().getCode();
        DeleteProjectResponse deleteResponse = ProjectAdapter.deleteProject(projectCode);
        softAssert.assertTrue(
                deleteResponse.getStatus(),
                "An error occurred when deleting the project"
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Create project without required fields",
            description = "Verifies that user can't create project without required fields",
            groups = {"smoke", "api", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Qase_01")
    @Feature("Project")
    @Story("Create project without required fields")
    public void createProjectWithoutRequiredFields() {
        SoftAssert softAssert = new SoftAssert();
        CreateProjectRequest request = ProjectFactory.invalidProjectRq();
        ErrorCreateProjectResponse createResponse = ProjectAdapter.createInvalidProject(request);
        softAssert.assertFalse(
                createResponse.getStatus(),
                "The project status is not false"
        );
        softAssert.assertEquals(
                createResponse.getErrorMessage(),
                "Data is invalid.",
                "Unexpected error message"
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Create project with empty body",
            description = "Verifies that user can't create project with empty body",
            groups = {"smoke", "api", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Qase_01")
    @Feature("Project")
    @Story("Create project with empty body")
    public void createProjectWithEmptyBody() {
        SoftAssert softAssert = new SoftAssert();
        CreateProjectRequest request = ProjectFactory.emptyProjectRq();
        ErrorCreateProjectResponse createResponse = ProjectAdapter.createInvalidProject(request);
        softAssert.assertFalse(
                createResponse.getStatus(),
                "The project status is not false"
        );
        softAssert.assertEquals(
                createResponse.getErrorMessage(),
                "Data is invalid.",
                "Unexpected error message"
        );
        softAssert.assertAll();
    }
}
