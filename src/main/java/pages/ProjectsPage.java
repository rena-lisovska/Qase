package pages;

import dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.PROJECTS_PAGE_TITLE;

@Log4j2
public class ProjectsPage extends BasePage {

    private final String PROJECT_NAME = "#project-name";
    private final String PROJECT_CODE = "#project-code";

    @Override
    @Step("Opening the Projects page")
    public ProjectsPage openPage() {
        log.info("Open projects page");
        open("/projects");
        return this;
    }

    @Override
    @Step("Projects page is opened")
    public ProjectsPage isPageOpened() {
        log.info("Projects page is opened");
        $(byText(PROJECTS_PAGE_TITLE)).shouldBe(visible);
        return this;
    }

    @Step("Create new project")
    public ProjectsPage createProject(String projectName, String projectCode) {
        log.info("Create project");
        $(byText(Elements.PROJECT_CREATE)).click();
        $(PROJECT_NAME).setValue(projectName);
        $(PROJECT_CODE).setValue(projectCode);
        $(byText(Elements.PROJECT_CREATE)).click();
        return this;
    }

    @Step("Checking the project for existence")
    public boolean checkProjectExists(String projectName) {
        log.info("Check project exists");
        return !$$("td").filterBy(text(projectName)).isEmpty();
    }

    @Step("Delete project")
    public ProjectsPage deleteProject(String project) {
        log.info("Delete project");
        $(byText(project))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        $("[data-testid=remove]").click();
        $x("//span[text()='Delete project']").click();
        return this;
    }
}
