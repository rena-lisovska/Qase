package core.utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AllureUtils {

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] takeScreenshot(String name, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void attachScreenshotIfPossible() {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            log.warn("WebDriver is not available. Screenshot was skipped.");
            return;
        }
        try {
            takeScreenshot("Failure screenshot", WebDriverRunner.getWebDriver());
        } catch (Exception e) {
            log.error("Failed to attach screenshot to Allure.", e);
        }
    }
}
