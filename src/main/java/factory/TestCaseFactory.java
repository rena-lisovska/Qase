package factory;

import api.models.testcase.request.CreateTestCaseRequest;
import com.github.javafaker.Faker;
import enums.*;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
public class TestCaseFactory {

    private static final Faker FAKER = new Faker(Locale.ENGLISH);

    public static CreateTestCaseRequest validTestCaseRq() {
        CreateTestCaseRequest testCase = CreateTestCaseRequest.builder()
                .title("Verify AUTO test through API " + FAKER.bothify("API-??##"))
                .description(FAKER.lorem().paragraph())
                .preconditions(FAKER.lorem().sentence())
                .postconditions(FAKER.lorem().sentence())
                .severity(SeverityType.random().getValue())
                .priority(PriorityType.random().getValue())
                .behavior(BehaviorType.random().getValue())
                .type(CaseType.random().getValue())
                .layer(LayerType.random().getValue())
                .status(StatusType.random().getValue())
                .isFlaky(FlakyType.random().getValue())
                .isManual(ThreadLocalRandom.current().nextBoolean())
                .isToBeAutomated(ThreadLocalRandom.current().nextBoolean())
                .attachments(List.of())
                .tags(List.of(
                        FAKER.hacker().noun(),
                        FAKER.app().name()
                ))
                .parameters(List.of())
                .build();
        log.info("Generated test case with all fields: [{}]", testCase);
        return testCase;
    }

    public static CreateTestCaseRequest minimalTestCaseRq() {
        CreateTestCaseRequest testCase = CreateTestCaseRequest.builder()
                .title("Verify AUTO test through API " + FAKER.bothify("API-??##"))
                .status(StatusType.random().getValue())
                .severity(SeverityType.random().getValue())
                .priority(PriorityType.random().getValue())
                .type(CaseType.random().getValue())
                .layer(LayerType.random().getValue())
                .isFlaky(FlakyType.random().getValue())
                .isManual(ThreadLocalRandom.current().nextBoolean())
                .build();
        log.info("Generated test case with required fields only: [{}]", testCase);
        return testCase;
    }

    public static CreateTestCaseRequest invalidTestCaseRq() {
        CreateTestCaseRequest testCase = CreateTestCaseRequest.builder()
                .severity(SeverityType.random().getValue())
                .priority(PriorityType.random().getValue())
                .behavior(BehaviorType.random().getValue())
                .type(CaseType.random().getValue())
                .layer(LayerType.random().getValue())
                .status(StatusType.random().getValue())
                .isFlaky(FlakyType.random().getValue())
                .isManual(ThreadLocalRandom.current().nextBoolean())
                .isToBeAutomated(ThreadLocalRandom.current().nextBoolean())
                .build();
        log.info("Generated test case without required fields: [{}", testCase);
        return testCase;
    }

    public static CreateTestCaseRequest emptyTestCaseRq() {
        CreateTestCaseRequest testCase = CreateTestCaseRequest.builder()
                .build();
        log.info("Generated empty test case");
        return testCase;
    }
}
