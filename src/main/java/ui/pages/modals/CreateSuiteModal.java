package ui.pages.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.dto.Suite;
import ui.pages.ProjectPage;
import ui.wrappers.ComboBox;
import ui.wrappers.Input;
import ui.wrappers.TextArea;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class CreateSuiteModal {

    private final Input suiteName = new Input($("#title"), "Suite name");
    private final ComboBox parentSuite = new ComboBox($("#parent_id"), "Parent suite");
    private final TextArea suiteDescription = new TextArea($("#description"), "Description");
    private final TextArea suitePreconditions = new TextArea($("#preconditions"), "Preconditions");

    @Step("Check that create suite modal is opened")
    public CreateSuiteModal isModalOpened() {
        log.info("Checking that create suite modal is opened");
        $(byText(CREATE_SUITE)).shouldBe(visible);
        $(byText(CREATE_BUTTON)).shouldBe(visible);
        $(byText(CANCEL_BUTTON)).shouldBe(visible);
        return this;
    }

    @Step("Fill create suite modal with suite data")
    public CreateSuiteModal fill(Suite suite) {
        log.info("Fill create suite modal: {}", suite);
        suiteName.setValue(suite.getName());
        suiteDescription.setValue(suite.getDescription());
        suitePreconditions.setValue(suite.getPreconditions());
        parentSuite.select(suite.getParentSuite());
        return this;
    }

    @Step("Click 'Create' suite")
    public CreateSuiteModal clickCreate() {
        log.info("Click create suite button");
        $(byText(CREATE_BUTTON)).shouldBe(enabled).click();
        return this;
    }

    @Step("Cancel suite creation")
    public CreateSuiteModal clickCancel() {
        log.info("Cansel suite creation");
        $(byText(CANCEL_BUTTON)).shouldBe(enabled).click();
        $(byText(CANCEL_BUTTON)).shouldNotBe(visible);
        return this;
    }

    @Step("Verify suite was created after click button 'Create'")
    public ProjectPage verifySuiteCreated(String suiteName) {
        log.info("Verify suite '{}' was created after click button 'Create'", suiteName);
        $(byText(CREATE_SUITE)).shouldNotBe(visible);
        $(byText(suiteName)).shouldBe(visible);
        return new ProjectPage();
    }

    @Step("Verify suite '{suiteName}' was not created after click button 'Cancel'")
    public ProjectPage verifySuiteNotCreated(String suiteName) {
        log.info("Verify suite '{}' was not created after click button 'Cancel'", suiteName);
        $(byText(CREATE_SUITE)).shouldNotBe(visible);
        $(byText(suiteName)).shouldNot(exist);
        return new ProjectPage();
    }
}
