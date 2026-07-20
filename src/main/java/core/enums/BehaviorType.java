package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum BehaviorType {

    NOT_SET(1),
    POSITIVE(2),
    NEGATIVE(3),
    DESTRUCTIVE(4);

    private final int value;

    BehaviorType(int value) {
        this.value = value;
    }

    public static BehaviorType random() {
        BehaviorType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
