package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum LayerType {

    NOT_SET(0),
    E2E(1),
    API(2),
    UNIT(3);

    private final int value;

    LayerType(int value) {
        this.value = value;
    }

    public static LayerType random() {
        LayerType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
