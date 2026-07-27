package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import ui.wrappers.Input;
import ui.wrappers.TextArea;
import java.io.File;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ui.dict.Elements.*;
import static ui.routes.UiRoutes.PROJECT_SETTINGS;

@Log4j2
public class ProjectSettingsPage extends BasePage {

    private final Input projectName = new Input($("#project-name"), "Project name");
    private final Input projectCode = new Input($("#project-code"), "Project code");
    private final TextArea description = new TextArea($("#description-area"), "Description");
    private static final String LOGO_PATH = System.getProperty("user.dir") + "/src/test/resources/test-file/logo/";

    @Override
    public ProjectSettingsPage openPage() {
        throw new UnsupportedOperationException(
                "Use ProjectsPage.openProjectSettings() instead");
    }

    @Override
    @Step("Check that project settings page is opened")
    public ProjectSettingsPage isPageOpened() {
        log.info("Checking that project settings page is opened");
        webdriver().shouldHave(urlContaining("/settings/general"));
        return this;
    }

    public ProjectSettingsPage isPageOpened(String projectCode) {
        webdriver().shouldHave(
                urlContaining(String.format(PROJECT_SETTINGS, projectCode))
        );
        return this;
    }

    @Step("Change project name to '{projectName}'")
    public ProjectSettingsPage changeProjectName(String projectName) {
        log.info("Change project name to '{}'", projectName);
        this.projectName.setValue(projectName);
        return this;
    }

    @Step("Change project code to '{projectCode}'")
    public ProjectSettingsPage changeProjectCode(String projectCode) {
        log.info("Change project code to '{}'", projectCode);
        this.projectCode.setValue(projectCode);
        return this;
    }

    @Step("Change project description")
    public ProjectSettingsPage changeDescription(String description) {
        log.info("Change project description");
        this.description.setValue(description);
        return this;
    }

    @Step("Upload project logo")
    public ProjectSettingsPage uploadLogo(String fileName) {
        log.info("Upload project logo: {}", fileName);
        $("input[type='file']")
                .uploadFile(new File(LOGO_PATH + fileName));
        return this;
    }

    @Step("Click 'Update settings'")
    public ProjectSettingsPage clickUpdate() {
        log.info("Click 'Update settings'");
        $(byText(UPDATE_SETTINGS_BUTTON)).click();
        return this;
    }

    @Step("Verify project settings updated successfully")
    public ProjectSettingsPage verifySettingsUpdated() {
        log.info("Verify project settings updated successfully");
        $(byText(PROJECT_SETTINGS_UPDATED))
                .shouldBe(visible);
        return this;
    }

    @Step("Verify logo upload failed")
    public ProjectSettingsPage verifyUploadFailed() {
        log.info("Verify invalid logo upload message is displayed");
        $(byText(INVALID_DATA))
                .shouldBe(visible);
        return this;
    }

    @Step("Verify project name update failed")
    public ProjectSettingsPage verifyProjectNameUpdateFailed() {
        log.info("Verify project name update failed");
        Assert.assertTrue(
                projectName.getValue().isEmpty(),
                "Project name should be empty"
        );
        $(byText(PROJECT_SETTINGS_UPDATED))
                .shouldNotBe(visible);
        return this;
    }

    @Step("Verify project code update failed")
    public ProjectSettingsPage verifyProjectCodeUpdateFailed() {
        log.info("Verify project code update failed");
        Assert.assertTrue(
                projectCode.getValue().isEmpty(),
                "Project code should be empty"
        );
        $(byText(PROJECT_SETTINGS_UPDATED))
                .shouldNotBe(visible);
        return this;
    }
}
