package configs;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class AllureConfig {
    private AllureConfig() {

    }

    public static void configure() {
        if (!SelenideLogger.hasListener("AllureSelenide")) {
            SelenideLogger.addListener(
                    "AllureSelenide",
                    new AllureSelenide()
                            .screenshots(true)
                            .savePageSource(true)
            );
        }
    }
}
