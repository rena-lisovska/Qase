package ui.wrappers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class ComboBox {

    private final SelenideElement element;
    private final String name;

    public ComboBox(SelenideElement element, String name) {
        this.element = element;
        this.name = name;
    }

    @Step("Open '{name}' combobox")
    public ComboBox open() {
        log.info("Open combobox '{}'", name);
        element.shouldBe(visible).click();
        return this;
    }

    @Step("Select '{value}' in '{name}' combobox")
    public ComboBox select(String value) {
        log.info("Select '{}' in combobox '{}'", value, name);
        open();
        $$("[role='option']")
                .findBy(text(value))
                .shouldBe(visible)
                .click();
        return this;
    }

    public String getValue() {
        return element
                .$("[data-display-value='true']")
                .shouldBe(visible)
                .getText();
    }
}
