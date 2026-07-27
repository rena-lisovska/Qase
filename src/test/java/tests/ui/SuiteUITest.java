package tests.ui;

import core.data.LoginTestData;
import core.factory.ui.UiProjectFactory;
import core.factory.ui.UiSuiteFactory;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.dto.Project;
import ui.dto.Suite;
import ui.pages.ProjectsPage;

public class SuiteUITest extends BaseTest {

    private Project project;
    private Suite suite;
    private String projectNameForDelete;

    @Test(
            testName = "Create suite with all fields",
            description = "Verify that user can successfully create a project with all available fields",
            groups = {"regression", "ui", "positive"}

    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Suites")
    @Feature("Suite UI creation")
    @Story("User creates a suite with all fields filled")
    public void checkCreateSuiteWithAllField() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.minimalProject();
        suite = UiSuiteFactory.validSuite();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenProjectPage(project)
                .clickCreateNewSuite();
        createSuiteModal.isModalOpened()
                .fill(suite)
                .clickCreate()
                .verifySuiteCreated(suite.getName());
        projectNameForDelete = project.getName();
    }

    @Test(
            testName = "Cancel suite creation",
            description = "Verify that suite is not created after clicking Cancel button",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Suites")
    @Feature("Suite UI creation")
    @Story("User cancels suite creation")
    public void checkSuiteIsNotCreationAfterCancel() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.minimalProject();
        suite = UiSuiteFactory.validSuite();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenProjectPage(project)
                .clickCreateNewSuite();
        createSuiteModal.isModalOpened()
                .fill(suite)
                .clickCancel()
                .verifySuiteNotCreated(suite.getName());
        projectNameForDelete = project.getName();
    }

    @Test(
            testName = "Import suite by valid file with .csv",
            description = "Verify that .csv file can be uploaded",
            groups = {"regression", "ui", "positive"}

    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Suites")
    @Feature("Suite UI creation")
    @Story("Import suite by valid file with .csv")
    public void checkUploadValidImportFile() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.minimalProject();
        suite = UiSuiteFactory.validSuite();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenProjectPage(project)
                .clickImport();
        importTestCasesModal.isModalOpened()
                .uploadFile("valid_suite.csv")
                .clickImport()
                .verifyImportSuccessMessage();
    }

    @Test(
            testName = "Import suite by invalid file with .txt",
            description = "Verify that .txt file cannot be uploaded",
            groups = {"regression", "ui", "negative"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Suites")
    @Feature("Suite UI creation")
    @Story("Import suite by invalid file with .txt")
    public void checkUploadInvalidImportFile() {
        LoginTestData loginData = LoginTestData.validCredentials();
        project = UiProjectFactory.minimalProject();
        suite = UiSuiteFactory.validSuite();
        loginStep.authorize(
                loginData.getUsername(),
                loginData.getPassword()
        );
        projectsStep.createProjectAndOpenProjectPage(project)
                .clickImport();
        importTestCasesModal.isModalOpened()
                .uploadFile("invalid_suite.txt")
                .clickImport()
                .verifyImportUnsuccessMessage();
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
            projectNameForDelete = null;
        }
    }
}
