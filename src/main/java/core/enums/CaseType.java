package core.enums;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum CaseType {

    OTHER(1),
    FUNCTIONAL(8),
    SMOKE(2),
    REGRESSION(3),
    SECURITY(4),
    USABILITY(5),
    PERFORMANCE(6),
    ACCEPTANCE(7),
    COMPATIBILITY(9),
    INTEGRATION(10),
    EXPLORATORY(11);

    private final int value;

    CaseType(int value) {
        this.value = value;
    }

    public static CaseType random() {
        CaseType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
