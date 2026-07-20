package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum BooleanType {

    FALSE(0),
    TRUE(1);

    private final int value;

    BooleanType(int value) {
        this.value = value;
    }

    public static BooleanType random() {
        BooleanType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
