package enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum FlakyType {

    NO(0),
    YES(1);

    private final int value;

    FlakyType(int value) {
        this.value = value;
    }

    public static FlakyType random() {
        FlakyType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
