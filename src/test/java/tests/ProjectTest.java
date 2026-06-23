package tests;

import dict.Elements;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectTest extends BaseTest {

    @Test (
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
        loginPage.openPage();
        loginPage.login(user, password);
        $(byText("Create new project")).click();
        $(byText(Elements.CREATE_NEW_PROJECT)).click();
        $("#project-name").setValue("TMS04");
        $("#project-code").setValue("TMS04");
        $(byText("Create project")).click();
//  добавить проверку, что проект создался
    }

    @AfterMethod
    public void checkDeleteProject() {
        open("/projects");
        projectsPage.deleteProject("TMS04");
//  добавить проверку, что проект удалился
    }
}
