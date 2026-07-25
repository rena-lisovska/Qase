package ui.pages;

import ui.routes.UiRoutes;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ui.dict.Elements.CREATE_NEW_PROJECT;
import static ui.dict.Elements.PROJECTS_PAGE_TITLE;

@Log4j2
public class ProjectsPage extends BasePage {

    @Override
    @Step("Open projects page")
    public ProjectsPage openPage() {
        log.info("Opening the Projects page");
        open(UiRoutes.PROJECTS);
        return this;
    }

    @Override
    @Step("Check that projects page is opened")
    public ProjectsPage isPageOpened() {
        log.info("Checking that projects page is opened");
        webdriver().shouldHave(urlContaining(UiRoutes.PROJECTS));
        $(byText(PROJECTS_PAGE_TITLE)).shouldBe(visible);
        return this;
    }

    @Step("Click create new project button")
    public CreateProjectModal clickCreateProject() {
        $(byText(CREATE_NEW_PROJECT)).click();
        return new CreateProjectModal();
    }

    @Step("Check if project '{projectName}' exists")
    public boolean isProjectExists(String projectName) {
        log.info("Check if project '{}' exists", projectName);
        return $$(
                "td")
                .findBy(text(projectName))
                .exists();
    }

    @Step("Delete project '{projectName}'")
    public ProjectsPage deleteProject(String projectName) {
        log.info("Delete project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        $("[data-testid=remove]").click();
        $x("//span[text()='Delete project']").click();
        $(byText(projectName)).shouldNotBe(visible);
        return this;
    }
}
