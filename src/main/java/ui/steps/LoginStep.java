package ui.steps;

import io.qameta.allure.Step;
import ui.pages.LoginPage;
import ui.pages.ProjectsPage;

public class LoginStep {

    private final LoginPage loginPage = new LoginPage();

    @Step("Authorize user")
    public ProjectsPage authorize(String user, String password) {
        ProjectsPage projectsPage = loginPage.openPage()
                .isPageOpened()
                .login(user, password);
        return projectsPage.isPageOpened();
    }
}
