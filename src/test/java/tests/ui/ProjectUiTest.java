package tests.ui;

import core.data.LoginTestData;
import ui.dto.Project;
import core.factory.ui.UiProjectFactory;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.pages.ProjectsPage;

public class ProjectUiTest extends BaseTest {

    private Project project;

    @Test(
            testName = "Create project with required fields",
            description = "Verify that user can successfully create project with required fields only",
            groups = {"smoke", "regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User creates project with required fields")
    public void checkCreateProjectWithRequiredFields() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.minimalProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProject(project)
                .verifyProjectName(project.getName());
    }

    @Test(
            testName = "Create project with all fields",
            description = "Verify that user can successfully create a project with all available fields",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User creates a project with all fields filled")
    public void checkCreateProjectWithAllFields() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProject(project)
                .verifyProjectName(project.getName());
    }

    @Test(
            testName = "Create public project",
            description = "Verify that user can create a public project",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User creates a project with public access")
    public void checkCreatePublicProject() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.publicProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProject(project)
                .verifyProjectName(project.getName());
    }

    @Test(
            testName = "Create project with group access",
            description = "Verify that user can create a project with group access and selected group",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User creates a project with group access")
    public void checkCreateProjectWithGroupAccess() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.groupAccessProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProject(project)
                .verifyProjectName(project.getName());
    }

    @Test(
            testName = "Create project without members",
            description = "Verify that user can create a project without adding members",
            groups = {"regression", "ui", "positive"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User creates a project without members")
    public void checkCreateProjectWithoutMembers() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.noMembersProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProject(project)
                .verifyProjectName(project.getName());
    }

    @Test(
            testName = "Create project without name",
            description = "Verify that project cannot be created without project name",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User cannot create project without required name")
    public void checkProjectWithoutName() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.withoutName();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsPage.clickCreateProject();
        createProjectModal
                .isModalOpened()
                .fill(project)
                .clickCreate()
                .verifyCreationFailed(project);
    }

    @Test(
            testName = "Create project without code",
            description = "Verify that project cannot be created without project code",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User cannot create project without required code")
    public void checkProjectWithoutCode() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.withoutCode();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsPage.clickCreateProject();
        createProjectModal
                .isModalOpened()
                .fill(project)
                .clickCreate()
                .verifyCreationFailed(project);
    }

    @Test(
            testName = "Create project without group selection",
            description = "Verify that project cannot be created when group access is selected without choosing a group",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User cannot create project with group access without selected group")
    public void checkGroupAccessWithoutGroup() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.groupAccessWithoutGroup();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsPage.clickCreateProject();
        createProjectModal
                .isModalOpened()
                .fill(project)
                .clickCreate()
                .verifyCreationFailed(project);
    }

    @Test(
            testName = "Create empty project",
            description = "Verify that project cannot be created with empty required fields",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User cannot create empty project")
    public void checkEmptyProject() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.emptyProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsPage.clickCreateProject();
        createProjectModal
                .isModalOpened()
                .fill(UiProjectFactory.emptyProject())
                .clickCreate()
                .verifyCreationFailed(project);
    }

    @Test(
            testName = "Cancel project creation",
            description = "Verify that project is not created after clicking Cancel button",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Projects")
    @Feature("Project UI creation")
    @Story("User cancels project creation")
    public void checkProjectIsNotCreatedAfterCancel() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.validProject();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.cancelProjectCreation(project)
                .isPageOpened();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteProjectAfterTest() {
        if (project == null) {
            return;
        }
        projectsPage = new ProjectsPage();
        try {
            projectsPage.openPage();
            if (projectsPage.isProjectExists(project.getName())) {
                projectsPage.deleteProject(project.getName());
            }
        } finally {
            project = null;
        }
    }
}
