package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static ui.routes.UiRoutes.PROJECT;

@Log4j2
public class ProjectPage {

    @Step("Project page is opened")
    public ProjectPage isPageOpened(String projectCode) {
        log.info("Checking that project page '{}' is opened", projectCode);
        webdriver().shouldHave(urlContaining(String.format(PROJECT, projectCode)));
        $("h1").shouldBe(visible).shouldHave(text(projectCode));
        return this;
    }

    @Step("Project name is '{projectName}'")
    public ProjectPage verifyProjectName(String projectName) {
        log.info("Checking project name '{}'", projectName);
        $("h2").shouldBe(visible).shouldHave(text(projectName));
        return this;
    }
}
