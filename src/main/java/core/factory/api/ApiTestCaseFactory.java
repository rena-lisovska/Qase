package core.factory.api;

import api.models.testcase.request.CreateTestCaseRequest;
import api.models.testcase.request.UpdateTestCaseRequest;
import com.github.javafaker.Faker;
import core.enums.*;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import java.util.Locale;

@Log4j2
public class ApiTestCaseFactory {

    private static final Faker FAKER = new Faker(Locale.ENGLISH);

    public static CreateTestCaseRequest validTestCaseRq() {
        CreateTestCaseRequest testCase = CreateTestCaseRequest.builder()
                .title("Verify test case through API " + FAKER.bothify("API-??##"))
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
                .isManual(BooleanType.random().getValue())
                .isToBeAutomated(BooleanType.random().getValue())
                .attachments(List.of())
                .tags(List.of(
                        FAKER.hacker().noun(),
                        FAKER.app().name()
                ))
                .parameters(List.of())
                .stepsType(StepsType.CLASSIC.getValue())
                .steps(List.of())
                .build();
        log.info("Generated test case with all fields: [{}]", testCase);
        return testCase;
    }

    public static UpdateTestCaseRequest updateTestCaseRq(CreateTestCaseRequest source) {
        UpdateTestCaseRequest testCase = UpdateTestCaseRequest.builder()
                .title(source.getTitle() + " Updated")
                .description(source.getDescription() + " Updated")
                .severity(SeverityType.CRITICAL.getValue())
                .build();
        log.info("Generated updated test case: [{}]", testCase);
        return testCase;
    }
}
