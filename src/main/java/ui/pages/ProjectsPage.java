package ui.pages;

import ui.pages.modals.CreateProjectModal;
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

    public static final String PROJECT_ACTION_MENU = "button[aria-label='Open action menu']";
    public static final String REMOVE_PROJECT_BUTTON = "[data-testid='remove']";
    public static final String DELETE_PROJECT_BUTTON = "Delete project";
    private static final String SETTINGS_PROJECT_BUTTON = "[data-testid='settings']";

    @Override
    @Step("Open projects page")
    public ProjectsPage openPage() {
        log.info("Opening the Projects page");
        open(UiRoutes.PROJECTS);
        return isPageOpened();
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
        if (projectName == null || projectName.isBlank()) {
            log.warn("Project name is empty, skip existence check");
            return false;
        }
        return $$("td")
                .findBy(text(projectName))
                .exists();
    }

    @Step("Delete project '{projectName}'")
    public ProjectsPage deleteProject(String projectName) {
        log.info("Delete project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(PROJECT_ACTION_MENU)
                .click();
        $(REMOVE_PROJECT_BUTTON)
                .shouldBe(visible)
                .click();
        $x(DELETE_PROJECT_BUTTON)
                .shouldBe(visible)
                .click();
        $(byText(projectName)).shouldNotBe(visible);
        return this;
    }

    @Step("Open settings for project '{projectName}'")
    public ProjectSettingsPage openProjectSettings(String projectName) {
        log.info("Open settings for project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(PROJECT_ACTION_MENU)
                .click();
        $(SETTINGS_PROJECT_BUTTON).click();
        return new ProjectSettingsPage();
    }

    @Step("Open project '{projectName}'")
    public ProjectPage openSpecificProject(String projectName) {
        log.info("Opening project '{}'", projectName);
        $(byText(projectName))
                .shouldBe(visible)
                .click();
        return new ProjectPage();
    }
}
