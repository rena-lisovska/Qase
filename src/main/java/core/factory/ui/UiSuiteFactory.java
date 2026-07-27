package core.factory.ui;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import ui.dto.Suite;

@Log4j2
public class UiSuiteFactory {

    private static final Faker FAKER = new Faker();

    public static Suite validSuite() {
        Suite suite = Suite.builder()
                .name(FAKER.bothify("SUITE###"))
                .description(FAKER.lorem().sentence())
                .parentSuite("Project root")
                .preconditions(FAKER.lorem().sentence())
                .build();
        log.info("Generated UI suite with all fields: [{}]", suite);
        return suite;
    }
}
