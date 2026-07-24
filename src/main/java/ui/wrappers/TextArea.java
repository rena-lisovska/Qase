package ui.wrappers;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

@Log4j2
public class TextArea {

    private final SelenideElement element;
    private final String name;

    public TextArea(SelenideElement element, String name) {
        this.element = element;
        this.name = name;
    }

    @Step("Set value '{value}' into '{name}' textarea")
    public TextArea setValue(String value) {
        log.info("Fill textarea '{}'", name);
        element.shouldBe(visible);
        element.setValue(value == null ? "" : value);
        return this;
    }

    @Step("Clear '{name}' textarea")
    public TextArea clear() {
        log.info("Clear textarea '{}'", name);
        element.shouldBe(visible);
        element.clear();
        return this;
    }

    public String getValue() {
        element.shouldBe(visible);
        return element.getValue();
    }
}
