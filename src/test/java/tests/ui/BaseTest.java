package tests.ui;

import core.configs.AllureConfig;
import core.configs.BrowserConfig;
import core.configs.TestConfig;
import io.qameta.allure.testng.AllureTestNg;
import core.listeners.TestListener;
import org.testng.annotations.*;
import ui.pages.LoginPage;
import ui.pages.ProjectPage;
import ui.pages.ProjectsPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected ProjectPage projectPage;

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
        projectsPage = new ProjectsPage();
        projectPage = new ProjectPage();
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
