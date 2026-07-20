package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ui.dict.Elements.*;

@Log4j2
public class LoginPage extends BasePage {

    private final String LOGIN = "[name=email]";
    private final String PASSWORD = "[name=password]";

    @Override
    @Step("Opening the Login page")
    public LoginPage openPage() {
        log.info("Open login page");
        open("/login");
        return this;
    }

    @Override
    @Step("Login page is opened")
    public LoginPage isPageOpened() {
        log.info("Login page is opened");
        $(byText(LOGIN_PAGE_TITLE)).shouldBe(visible);
        return this;
    }

    @Step("Positive login in system")
    public ProjectsPage login(String user, String password) {
        log.info("Login to Qase");
        if ($("usercentrics-cmp-ui").exists()) {
            $(shadowCss("#accept", "#usercentrics-cmp-ui"))
                    .click();
        }
        $(LOGIN).setValue(user);
        $(PASSWORD).setValue(password);
        $(byText(SIGN_IN)).click();
        $(byText(PROJECTS_PAGE_TITLE)).shouldBe(visible);
        return new ProjectsPage();
    }
}
