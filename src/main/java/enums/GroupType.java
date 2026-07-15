package enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum GroupType {

    QA("QA"),
    AUTO("AUTO"),
    MANUAL("MANUAL"),
    DEV("DEV"),
    OTHER("OTHER");

    private final String value;

    GroupType(String value) {
        this.value = value;
    }

    public static GroupType random() {
        GroupType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
