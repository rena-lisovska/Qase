package tests.ui;

import core.data.LoginTestData;
import core.utils.PropertyReader;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    @Test(
            testName = "Login with valid credentials",
            description = "Verify that user can successfully log in with valid credentials",
            groups = {"smoke", "regression"}

    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Authentication")
    @Feature("Login")
    @Story("User logs in with valid credentials")
    public void checkLoginSuccessful() {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password);
        projectsPage.isPageOpened();
    }

    @DataProvider(name = "emptyLoginFields")
    public Object[][] emptyLoginFields() {
        return new Object[][]{
                {"", "password"},
                {"user", ""},
                {"", ""}
        };
    }

    @Test(
            dataProvider = "emptyLoginFields",
            testName = "Login with empty required fields",
            description = "Verify that validation messages are displayed when user submits login form with empty required fields",
            groups = {"regression"}

    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Authentication")
    @Feature("Login")
    @Story("User cannot log in without required fields")
    public void checkRequiredFieldsValidation(String username, String password) {
        loginPage.openPage()
                .isPageOpened()
                .loginExpectingError(username, password);
        loginPage.verifyRequiredFieldsMessage();
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        String validUser = PropertyReader.getProperty("user");
        String validPassword = PropertyReader.getProperty("password");
        return new Object[][]{
                {
                        new LoginTestData(validUser, "invalid_password_1234")
                },
                {
                        new LoginTestData("some_emailname@gmail.com", validPassword)
                }
        };
    }

    @Test(
            dataProvider = "invalidCredentials",
            testName = "Login with invalid credentials",
            description = "Verify that user cannot log in with incorrect username or password",
            groups = {"regression"}

    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Authentication")
    @Feature("Login")
    @Story("User cannot log in with invalid credentials")
    public void checkLoginWithInvalidCredentials(LoginTestData loginData) {
        loginPage.openPage()
                .isPageOpened()
                .loginExpectingError(
                        loginData.getUsername(),
                        loginData.getPassword());
        loginPage.verifyNotMatchRecordsMessage();
    }

    @Test(
            testName = "Remember me checkbox state",
            description = "Verify that user can enable and disable Remember me checkbox",
            groups = {"regression"}

    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Authentication")
    @Feature("Login")
    @Story("User can manage Remember me option")
    public void checkRememberMeCheckboxState() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage()
                .isPageOpened()
                .disableRememberMeCheckbox();
        softAssert.assertFalse(
                loginPage.isRememberMeCheckboxSelected(),
                "'Remember me' checkbox should be unchecked");
        loginPage.enableRememberMeCheckbox();
        softAssert.assertTrue(
                loginPage.isRememberMeCheckboxSelected(),
                "'Remember me' checkbox should be checked");
        softAssert.assertAll();
    }
}
