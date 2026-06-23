package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    public ProjectPage isPageOpened(String projectName) {
        $("h2").shouldBe(visible).shouldHave(text(projectName));
        return this;
    }
}
