package enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum PriorityType {

    NOT_SET(0),
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int value;

    PriorityType(int value) {
        this.value = value;
    }

    public static PriorityType random() {
        PriorityType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

}
