package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProjectPage {

    @Step("Project page is opened")
    public ProjectPage isPageOpened(String projectName) {
        log.info("Project page is opened");
        $("h2").shouldBe(visible).shouldHave(text(projectName));
        return this;
    }
}
