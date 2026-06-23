package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static dict.Elements.SIGN_IN;

public class LoginPage extends BasePage {

    private final String LOGIN = "[name=email]";
    private final String PASSWORD = "[name=password]";

    @Override
    @Step("Opening the Login page")
    public LoginPage openPage() {
        open("/login");
        return this;
    }

    @Override
    @Step("Login page is opened")
    public LoginPage isPageOpened() {

        return this;
    }

    @Step("Positive login")
    public void login(String user, String password){
        $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
        $(LOGIN).setValue(user);
        $(PASSWORD).setValue(password);
        $(byText(SIGN_IN)).click();
    }
}
