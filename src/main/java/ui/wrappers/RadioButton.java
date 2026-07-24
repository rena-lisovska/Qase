package ui.wrappers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.visible;

@Log4j2
public class RadioButton {

    private final SelenideElement element;
    private final String name;

    public RadioButton(SelenideElement element, String name) {
        this.element = element;
        this.name = name;
    }

    @Step("Select '{name}' radio button")
    public RadioButton select() {
        log.info("Select radio button '{}'", name);
        element.shouldBe(visible).click();
        return this;
    }

    @Step("Verify '{name}' radio button is selected")
    public RadioButton shouldBeSelected() {
        log.info("Check that radio button '{}' is selected", name);
        element.shouldBe(checked);
        return this;
    }

    @Step("Verify '{name}' radio button is not selected")
    public RadioButton shouldBeNotSelected() {
        log.info("Check that radio button '{}' is not selected", name);
        element.shouldNotBe(checked);
        return this;
    }

    public boolean isSelected() {
        return element.isSelected();
    }
}
