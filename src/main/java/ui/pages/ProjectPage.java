package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.pages.modals.CreateSuiteModal;
import ui.pages.modals.ImportTestCasesModal;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ui.dict.Elements.*;
import static ui.routes.UiRoutes.PROJECT;

@Log4j2
public class ProjectPage extends BasePage {

    private static final String PROJECT_CODE_TITLE = "h1";
    private static final String PROJECT_NAME_TITLE = "h2";

    @Override
    public ProjectPage openPage() {
        throw new UnsupportedOperationException(
                "Use openPage(String projectCode) instead");
    }

    @Step("Open project page '{projectCode}'")
    public ProjectPage openPage(String projectCode) {
        log.info("Opening project page '{}'", projectCode);
        open(String.format(PROJECT, projectCode));
        return this;
    }

    @Override
    public ProjectPage isPageOpened() {
        throw new UnsupportedOperationException(
                "Use openPage(String projectCode) instead");
    }

    @Step("Project page is opened")
    public ProjectPage isPageOpened(String projectCode) {
        log.info("Checking that project page '{}' is opened", projectCode);
        webdriver().shouldHave(urlContaining(String.format(PROJECT, projectCode)));
        $(PROJECT_CODE_TITLE).shouldBe(visible).shouldHave(text(projectCode));
        return this;
    }

    @Step("Project name is '{projectName}'")
    public ProjectPage verifyProjectName(String projectName) {
        log.info("Checking project name '{}'", projectName);
        $(PROJECT_NAME_TITLE)
                .shouldBe(visible)
                .shouldHave(text(projectName));
        return this;
    }

    @Step("Click 'Create new suite'")
    public CreateSuiteModal clickCreateNewSuite() {
        log.info("Click 'Create new suite'");
        $(byText(CREATE_NEW_SUITE)).click();
        return new CreateSuiteModal();
    }

    @Step("Click 'Import'")
    public ImportTestCasesModal clickImport() {
        log.info("Click Import");
        $(byText(IMPORT_BUTTON)).click();
        return new ImportTestCasesModal();
    }
}
