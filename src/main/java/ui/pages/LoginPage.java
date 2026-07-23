package ui.pages;

import com.codeborne.selenide.SelenideElement;
import ui.routes.UiRoutes;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ui.dict.Elements.*;

@Log4j2
public class LoginPage extends BasePage {

    private static final String LOGIN_INPUT = "[name=email]";
    private static final String PASSWORD_INPUT = "[name=password]";
    private static final String COOKIE_ACCEPT = "#accept";
    private static final String COOKIE_BANNER = "#usercentrics-cmp-ui";
    private static final String REQUIRED_FIELDS_MESSAGE = "//small[contains(text(), 'This field is required')]";
    private static final String NOT_MATCH_RECORDS_MESSAGE = "//div[@role='alert']//span[contains(text(), 'These credentials do not match our records')]";
    private static final String REMEMBER_ME_CHECKBOX = "input[name='remember']";
    private static final String REMEMBER_ME_CONTAINER = "[data-sentry-component='Checkbox']";

    @Override
    @Step("Open login page")
    public LoginPage openPage() {
        log.info("Opening login page");
        open(UiRoutes.LOGIN);
        acceptCookies();
        return this;
    }

    @Override
    @Step("Check that login page is opened")
    public LoginPage isPageOpened() {
        log.info("Checking that login page is open");
        webdriver().shouldHave(urlContaining(UiRoutes.LOGIN));
        $(byText(LOGIN_PAGE_TITLE))
                .shouldBe(visible
                        .because("Login page title should be displayed when login page is opened"));
        return this;
    }

    @Step("Login with positive credentials")
    public ProjectsPage login(String user, String password) {
        log.info("Login with positive credentials");
        $(LOGIN_INPUT).setValue(user);
        $(PASSWORD_INPUT).setValue(password);
        $(byText(SIGN_IN_BUTTON)).click();
        return new ProjectsPage();
    }

    @Step("Attempt login with invalid credentials")
    public LoginPage loginExpectingError(String user, String password) {
        log.info("Attempting login with invalid credentials");
        $(LOGIN_INPUT).setValue(user);
        $(PASSWORD_INPUT).setValue(password);
        $(byText(SIGN_IN_BUTTON)).click();
        return this;
    }

    @Step("Verify required fields validation message")
    public LoginPage verifyRequiredFieldsMessage() {
        log.info("Verifying required fields validation message");
        $x(REQUIRED_FIELDS_MESSAGE)
                .shouldBe(visible
                        .because("Required fields validation message should be displayed when login fields are empty"));
        return this;
    }

    @Step("Verify invalid credentials validation message")
    public LoginPage verifyNotMatchRecordsMessage() {
        log.info("Verifying invalid credentials validation message");
        $x(NOT_MATCH_RECORDS_MESSAGE)
                .shouldBe(visible
                        .because("Invalid credentials error message should be displayed after login attempt with incorrect credentials"));
        return this;
    }

    @Step("Enable Remember me checkbox")
    public LoginPage enableRememberMeCheckbox() {
        log.info("Enabling 'Remember me' checkbox");
        return setRememberMe(true);
    }

    @Step("Disable Remember me checkbox")
    public LoginPage disableRememberMeCheckbox() {
        log.info("Disabling 'Remember me' checkbox");
        return setRememberMe(false);
    }

    @Step("Check Remember me checkbox state")
    public boolean isRememberMeCheckboxSelected() {
        log.info("Checking 'Remember me' checkbox state");
        return $(REMEMBER_ME_CHECKBOX).isSelected();
    }

    private void acceptCookies() {
        log.info("Accepting cookies");
        SelenideElement cookieBanner = $(COOKIE_BANNER);
        SelenideElement cookieButton = $(shadowCss(COOKIE_ACCEPT, COOKIE_BANNER));
        if (cookieButton.exists()) {
            cookieButton.shouldBe(visible).click();
            cookieBanner.should(disappear);
        }
    }

    private LoginPage setRememberMe(boolean enabled) {
        SelenideElement checkbox = $(REMEMBER_ME_CHECKBOX);
        if (checkbox.isSelected() != enabled) {
            $(REMEMBER_ME_CONTAINER)
                    .shouldBe(visible)
                    .click();
        }
        return this;
    }
}
