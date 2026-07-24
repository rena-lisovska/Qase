package core.configs;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class AllureConfig {

    private AllureConfig() {
    }

    public static void enableListener() {
        if (!SelenideLogger.hasListener("AllureSelenide")) {
            SelenideLogger.addListener(
                    "AllureSelenide",
                    new AllureSelenide()
                            .screenshots(true)
                            .savePageSource(true)
            );
        }
    }

    public static void disableListener() {
        SelenideLogger.removeListener("AllureSelenide");
    }
}
