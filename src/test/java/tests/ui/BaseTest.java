package tests.ui;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import ui.pages.LoginPage;
import ui.pages.ProjectsPage;
import utils.PropertyReader;
import java.util.HashMap;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod (alwaysRun = true, description = "Configuration and launch browser")
    public void setUp(@Optional("chrome") String browser) {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.headless = true;


        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        Configuration.browserCapabilities = options;

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    @AfterMethod (alwaysRun = true, description = "Close browser")
    public void tearDown() {
        getWebDriver().quit();
    }
}
