package tests;

import dict.Elements;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectTest extends BaseTest {

    @Test(
            testName = "Creating a project",
            description = "Verifying the creation of a new project",
            groups = "smoke"
    )
    @Owner("Lisovskaya Ira")
    @Epic("Qase 1")
    @Feature("New_project")
    @Story("Create project")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateProject() {
        loginPage.openPage()
                .login(user, password);
        projectsPage.isPageOpened()
                .createProject("TMS05", "TMS05")
                .checkProjectExists("TMS05");
    }

    @AfterMethod
    public void checkDeleteProject() {
        projectsPage.openPage()
                .deleteProject("TMS05");
    }
}
