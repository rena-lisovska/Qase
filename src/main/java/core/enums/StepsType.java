package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum StepsType {

    CLASSIC("classic"),
    GHERKIN("gherkin");

    private final String value;

    StepsType(String value) {
        this.value = value;
    }

    public static StepsType random() {
        StepsType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
