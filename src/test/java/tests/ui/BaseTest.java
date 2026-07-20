package tests.ui;

import configs.AllureConfig;
import configs.BrowserConfig;
import configs.TestConfig;
import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.testng.annotations.*;
import ui.pages.LoginPage;
import ui.pages.ProjectsPage;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;

    protected String user;
    protected String password;

    @BeforeMethod(
            alwaysRun = true,
            description = "Configuration and launch browser"
    )
    public void setUp() {
        AllureConfig.configure();
        BrowserConfig.configure(TestConfig.getBrowser());
        user = TestConfig.getUser();
        password = TestConfig.getPassword();
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
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
