package core.utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public final class SecureSelenide {

    private SecureSelenide() {
    }

    @Step("Fill secret field")
    public static void setSecretValue(SelenideElement element, String value) {
        element.getWrappedElement().sendKeys(value);
    }
}
