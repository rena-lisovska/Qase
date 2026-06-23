package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage {

    @Step("Deleting a project")
    public void deleteProject(String project) {
        $(byText(project))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        $("[data-testid=remove]").click();
        $x("//span[text()='Delete project']").click();
    }
}
