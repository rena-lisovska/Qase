package ui.pages.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.pages.ProjectPage;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class ImportTestCasesModal {

    private static final String IMPORT_MODAL_TITLE = "h3";
    private static final String IMPORT_BUTTON = "#import-data-confirm-button";
    private static final String CHOOSE_FILE = "input[type='file']";
    private static final String SUITE_PATH = System.getProperty("user.dir") + "/src/test/resources/test-file/suites/";

    @Step("Check that import test case modal is opened")
    public ImportTestCasesModal isModalOpened() {
        log.info("Checking that import test case modal is opened");
        $(IMPORT_MODAL_TITLE)
                .shouldBe(visible)
                .shouldHave(text(IMPORT_TEST_CASES));
        return this;
    }

    @Step("Select and upload file")
    public ImportTestCasesModal uploadFile(String fileName) {
        log.info("Select and upload file: {}", fileName);
        $(CHOOSE_FILE).uploadFile(new File(SUITE_PATH + fileName));
        return this;
    }

    @Step("Click 'Import test case'")
    public ImportTestCasesModal clickImport() {
        log.info(("Click button 'Import test case'"));
        $(IMPORT_BUTTON).click();
        return this;
    }

    @Step("Verify that new data was successfully imported")
    public ProjectPage verifyImportSuccessMessage() {
        log.info("Verify that new data was successfully imported");
        $(withText("were successfully imported!")).shouldBe(visible);
        return new ProjectPage();
    }

    @Step("Verify suite upload failed")
    public ImportTestCasesModal verifyImportUnsuccessMessage() {
        log.info("Verify invalid file upload message is displayed");
        $(byText(INVALID_DATA)).shouldBe(visible);
        return this;
    }
}
