package tests.ui;

import core.data.LoginTestData;
import core.factory.ui.UiProjectFactory;
import core.factory.ui.UiProjectUpdateFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import ui.dto.Project;
import ui.pages.ProjectsPage;

public class ProjectSettingsUiTest extends BaseTest {

    private Project project;
    private Project updatedProject;
    private String projectNameForDelete;

    @Test(
            testName = "Update project with new data",
            description = "Verify that project name, code and description can be fully updated",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI settings")
    @Story("Update all project fields")
    public void checkUpdateProjectWithNewData() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        updatedProject = UiProjectUpdateFactory.updateAllFields();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenSettings(project)
                .changeProjectName(updatedProject.getName())
                .changeProjectCode(updatedProject.getCode())
                .changeDescription(updatedProject.getDescription())
                .clickUpdate()
                .verifySettingsUpdated();
        projectNameForDelete = updatedProject.getName();
    }

    @Test(
            testName = "Append Update to project fields",
            description = "Verify that project fields can be updated by appending 'Update'",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI settings")
    @Story("Append Update to project fields")
    public void checkAppendUpdateToProjectFields() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        updatedProject = UiProjectUpdateFactory.appendUpdate(project);
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenSettings(project)
                .changeProjectName(updatedProject.getName())
                .changeProjectCode(updatedProject.getCode())
                .changeDescription(updatedProject.getDescription())
                .clickUpdate()
                .verifySettingsUpdated();
        projectNameForDelete = updatedProject.getName();
    }

    @Test(
            testName = "Project name is required on update",
            description = "Verify that project cannot be updated without name",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI settings")
    @Story("Update project without name")
    public void checkUpdateProjectWithoutName() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        updatedProject = UiProjectUpdateFactory.clearProjectName(project);
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenSettings(project)
                .changeProjectName(updatedProject.getName())
                .clickUpdate()
                .verifyProjectNameUpdateFailed();
        projectNameForDelete = project.getName();
    }

    @Test(
            testName = "Project code is required on update",
            description = "Verify that project cannot be updated without code",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI settings")
    @Story("Update project without code")
    public void checkUpdateProjectWithoutCode() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        updatedProject = UiProjectUpdateFactory.clearProjectCode(project);
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenSettings(project)
                .changeProjectCode(updatedProject.getCode())
                .clickUpdate()
                .verifyProjectCodeUpdateFailed();
        projectNameForDelete = project.getName();
    }

    @Test(
            testName = "Upload valid project logo",
            description = "Verify that .jpg logo can be uploaded",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI settings")
    @Story("Upload valid project logo")
    public void checkUploadValidLogo() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenSettings(project)
                .uploadLogo("valid_logo.jpg")
                .clickUpdate()
                .verifySettingsUpdated();
        projectNameForDelete = project.getName();
    }

    @Test(
            testName = "Upload invalid project logo",
            description = "Verify that .txt file cannot be uploaded as project logo",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI settings")
    @Story("Upload invalid project logo")
    public void checkUploadInvalidLogo() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenSettings(project)
                .uploadLogo("invalid_logo.txt")
                .verifyUploadFailed();
        projectNameForDelete = project.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteProjectAfterTest() {
        if (projectNameForDelete == null) {
            return;
        }
        ProjectsPage projectsPage = new ProjectsPage();
        try {
            projectsPage.openPage();
            if (projectsPage.isProjectExists(projectNameForDelete)) {
                projectsPage.deleteProject(projectNameForDelete);
            }
        } finally {
            project = null;
            updatedProject = null;
            projectNameForDelete = null;
        }
    }
}
