package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum StatusType {

    ACTUAL(0),
    DRAFT(1),
    DEPRECATED(2);

    private final int value;

    StatusType(int value) {
        this.value = value;
    }

    public static StatusType random() {
        StatusType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
