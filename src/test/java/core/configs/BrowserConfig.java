package core.configs;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;

public class BrowserConfig {

    private BrowserConfig() {
    }

    public static void configure(String browser) {
        browser = browser.toLowerCase();
        Configuration.browser = browser;
        Configuration.baseUrl = TestConfig.getBaseUrl();
        Configuration.timeout = 30000;
        Configuration.clickViaJs = true;

        switch (browser) {
            case "chrome":
                Configuration.browserCapabilities = chromeOptions();
                break;

            case "firefox":
                Configuration.browserCapabilities = firefoxOptions();
                break;

            case "edge":
                Configuration.browserCapabilities = edgeOptions();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser:" + browser
                );
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--incognito",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--window-size=1920,1080",
                "--disable-infobars");
        options.setAcceptInsecureCerts(true);
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.disable_open_during_load", false);
        options.addArguments(
                "--headless",
                "--width=1920",
                "--height=1080");
        options.setAcceptInsecureCerts(true);
        return options;
    }

    private static EdgeOptions edgeOptions() {
        EdgeOptions options = new EdgeOptions();
        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("credentials_enable_service", false);
        edgePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", edgePrefs);
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--inprivate",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--window-size=1920,1080");
        options.setAcceptInsecureCerts(true);
        return options;
    }
}
