package ui.pages;

import core.dto.Project;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.ComboBox;
import ui.wrappers.Input;
import ui.wrappers.RadioButton;
import ui.wrappers.TextArea;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.dict.Elements.*;

@Log4j2
public class CreateProjectModal {
    private final Input projectName = new Input($("#project-name"), "Project name");
    private final Input projectCode = new Input($("#project-code"), "Project code");
    private final TextArea description = new TextArea($("#description-area"), "Description");
    private final RadioButton privateAccess = new RadioButton($("input[value='private']"), "Private");
    private final RadioButton publicAccess = new RadioButton($("input[value='public']"), "Public");
    private final RadioButton memberAll = new RadioButton($("input[value='all']"), "Add all members to this project");
    private final RadioButton memberGroup = new RadioButton($("input[value='group']"), "Group access");
    private final RadioButton memberNone = new RadioButton($("input[value='none']"), "Don't add members");
    private final ComboBox chooseGroup = new ComboBox($x("//label[normalize-space()='Choose a group']/following::div[@role='combobox'][1]"), "Choose a group");

    @Step("Check that create project modal is opened")
    public CreateProjectModal shouldBeOpened() {
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

    @Step("Create project")
    public ProjectPage clickCreate() {
        log.info("Click create project button");
        $(byText(CREATE_PROJECT_BUTTON)).click();
        return new ProjectPage();
    }

    @Step("Cancel project creation")
    public ProjectsPage clickCancel() {
        log.info("Cancel project creation");
        $(byText(CANCEL_BUTTON)).click();
        return new ProjectsPage();
    }

    private void selectAccessType(Project project) {
        if (project.getAccessType() == null) {
            return;
        }
        switch (project.getAccessType()) {
            case "private" -> privateAccess.select();
            case "public" -> publicAccess.select();
            default -> throw new IllegalArgumentException("Unknown access type: " + project.getAccessType());
        }
    }

    private void selectMemberAccess(Project project) {
        if (project.getMemberAccess() == null) {
            return;
        }
        switch (project.getMemberAccess()) {
            case "all" -> memberAll.select();
            case "group" -> {
                memberGroup.select();
                if (project.getGroup() != null) {
                    chooseGroup.select(project.getGroup());
                }
            }
            case "none" -> memberNone.select();
            default -> throw new IllegalArgumentException("Unknown member access: " + project.getMemberAccess());
        }
    }

}
