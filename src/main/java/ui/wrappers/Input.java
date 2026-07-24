package ui.wrappers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;

@Log4j2
public class Input {

    private final SelenideElement element;
    private final String name;


    public Input(SelenideElement element, String name) {
        this.element = element;
        this.name = name;
    }

    @Step("Set value '{value}' into '{name}' input")
    public Input setValue(String value) {
        log.info("Fill input '{}'", name);
        element.shouldBe(visible).setValue(value == null ? "" : value);
        return this;
    }

    @Step("Clear '{name}' input")
    public Input clear() {
        log.info("Clear input '{}'", name);
        element.shouldBe(visible).clear();
        return this;
    }

    public String getValue() {
        return element.getValue();
    }
}
