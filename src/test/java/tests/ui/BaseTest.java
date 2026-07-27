package tests.ui;

import core.configs.AllureConfig;
import core.configs.BrowserConfig;
import core.configs.TestConfig;
import io.qameta.allure.testng.AllureTestNg;
import core.listeners.TestListener;
import org.testng.annotations.*;
import ui.pages.*;
import ui.pages.modals.CreateProjectModal;
import ui.pages.modals.CreateSuiteModal;
import ui.pages.modals.ImportTestCasesModal;
import ui.steps.LoginStep;
import ui.steps.ProjectsStep;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected LoginPage loginPage;
    protected LoginStep loginStep;
    protected ProjectsPage projectsPage;
    protected CreateProjectModal createProjectModal;
    protected ProjectsStep projectsStep;
    protected ProjectPage projectPage;
    protected ProjectSettingsPage projectSettingsPage;
    protected CreateSuiteModal createSuiteModal;
    protected ImportTestCasesModal importTestCasesModal;

    protected String user;
    protected String password;

    @BeforeMethod(
            alwaysRun = true,
            description = "Configuration and launch browser"
    )
    public void setUp() {
        configureEnvironment();
        loadTestData();
        initPageObjects();
    }

    protected void configureEnvironment() {
        AllureConfig.enableListener();
        BrowserConfig.configure(TestConfig.getBrowser());
    }

    protected void loadTestData() {
        user = TestConfig.getUser();
        password = TestConfig.getPassword();
    }

    protected void initPageObjects() {
        loginPage = new LoginPage();
        loginStep = new LoginStep();
        projectsPage = new ProjectsPage();
        projectsStep = new ProjectsStep();
        createProjectModal = new CreateProjectModal();
        projectPage = new ProjectPage();
        projectSettingsPage = new ProjectSettingsPage();
        createSuiteModal = new CreateSuiteModal();
        importTestCasesModal = new ImportTestCasesModal();
    }

    @AfterMethod(
            alwaysRun = true,
            description = "Close browser"
    )
    public void tearDown() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}
