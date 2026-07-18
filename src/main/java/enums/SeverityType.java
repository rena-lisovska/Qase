package enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum SeverityType {

    NOT_SET(0),
    BLOCKER(1),
    CRITICAL(2),
    MAJOR(3),
    NORMAL(4),
    MINOR(5),
    TRIVIAL(6);

    private final int value;

    SeverityType(int value) {
        this.value = value;
    }

    public static SeverityType random() {
        SeverityType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
