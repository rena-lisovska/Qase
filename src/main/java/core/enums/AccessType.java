package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum AccessType {

    ALL("all"),
    GROUP("group"),
    NONE("none");

    private final String value;

    AccessType(String value) {
        this.value = value;
    }

    public static AccessType random() {
        AccessType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
