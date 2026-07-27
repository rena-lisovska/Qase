package ui.pages.modals;

import org.testng.Assert;
import ui.dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.pages.ProjectsPage;
import ui.wrappers.ComboBox;
import ui.wrappers.Input;
import ui.wrappers.RadioButton;
import ui.wrappers.TextArea;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class CreateProjectModal {

    private final Input projectName = new Input($("#project-name"), "Project name");
    private final Input projectCode = new Input($("#project-code"), "Project code");
    private final TextArea description = new TextArea($("#description-area"), "Description");
    private final RadioButton privateAccess = new RadioButton($x("//input[@value='private']/ancestor::label"), "Private");
    private final RadioButton publicAccess = new RadioButton($x("//input[@value='public']/ancestor::label"), "Public");
    private final RadioButton memberAll = new RadioButton($x("//input[@value='all']/ancestor::label"), "Add all members");
    private final RadioButton memberGroup = new RadioButton($x("//input[@value='group']/ancestor::label"), "Group access");
    private final RadioButton memberNone = new RadioButton($x("//input[@value='none']/ancestor::label"), "Don't add members");
    private final ComboBox chooseGroup = new ComboBox($x("//label[normalize-space()='Choose a group']/following::div[@role='combobox'][1]"), "Choose a group");

    @Step("Check that create project modal is opened")
    public CreateProjectModal isModalOpened() {
        log.info("Checking that create project modal is opened");
        $(byText(CREATE_PROJECT_BUTTON)).shouldBe(visible);
        $(byText(CANCEL_BUTTON)).shouldBe(visible);
        return this;
    }

    @Step("Fill create project modal with project data")
    public CreateProjectModal fill(Project project) {
        log.info("Fill create project modal: {}", project);
        projectName.setValue(project.getName());
        projectCode.setValue(project.getCode());
        if (project.getDescription() != null) {
            description.setValue(project.getDescription());
        }
        selectAccessType(project);
        selectMemberAccess(project);
        return this;
    }

    @Step("Click 'Create project'")
    public CreateProjectModal clickCreate() {
        log.info("Click create project button");
        $(byText(CREATE_PROJECT_BUTTON)).click();
        return this;
    }

    @Step("Cancel project creation")
    public ProjectsPage clickCancel() {
        log.info("Cancel project creation");
        $(byText(CANCEL_BUTTON)).shouldBe(enabled).click();
        $(byText(CANCEL_BUTTON)).shouldNotBe(visible);
        return new ProjectsPage().isPageOpened();
    }

    @Step("Verify project creation failed and modal stays open")
    public CreateProjectModal verifyCreationFailed(Project project) {
        log.info("Verify project creation failed for project: {}", project);
        $(byText(CREATE_PROJECT_BUTTON)).shouldBe(visible);
        if (project.getName() != null) {
            Assert.assertEquals(
                    projectName.getValue(),
                    project.getName(),
                    "Project name should remain in input field"
            );
        }
        if (project.getCode() != null) {
            Assert.assertEquals(
                    projectCode.getValue(),
                    project.getCode(),
                    "Project code should remain in input field"
            );
        }
        return this;
    }

    private void selectAccessType(Project project) {
        if (project.getAccessType() == null) {
            return;
        }
        if ("public".equals(project.getAccessType())) {
            publicAccess.select();
        }
    }

    private void selectMemberAccess(Project project) {
        if (project.getMemberAccess() == null) {
            return;
        }
        switch (project.getMemberAccess()) {
            case "group" -> {
                memberGroup.select();
                if (project.getGroup() != null) {
                    chooseGroup.select(project.getGroup());
                }
            }
            case "none" -> memberNone.select();
            case "all" -> {
            }
            default -> throw new IllegalArgumentException(
                    "Unknown member access: " + project.getMemberAccess());
        }
    }
}
