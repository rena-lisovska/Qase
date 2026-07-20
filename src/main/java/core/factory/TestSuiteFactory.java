package core.factory;

import api.models.testsuite.request.CreateUpdateTestSuiteRequest;
import api.models.testsuite.request.DeleteTestSuiteRequest;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import java.util.Locale;

@Log4j2
public class TestSuiteFactory {

    private static final Faker FAKER = new Faker(Locale.ENGLISH);

    public static CreateUpdateTestSuiteRequest validTestSuiteRq() {
        CreateUpdateTestSuiteRequest testSuite = CreateUpdateTestSuiteRequest.builder()
                .title("Verify test suite through API " + FAKER.bothify("API-??##"))
                .description(FAKER.lorem().paragraph())
                .preconditions(FAKER.lorem().sentence())
                .build();
        log.info("Generated test suite with all fields: [{}]", testSuite);
        return testSuite;
    }

    public static CreateUpdateTestSuiteRequest invalidTestSuiteRq() {
        CreateUpdateTestSuiteRequest testSuite = CreateUpdateTestSuiteRequest.builder()
                .description(FAKER.lorem().paragraph())
                .preconditions(FAKER.lorem().sentence())
                .build();
        log.info("Generated test suite with missing title field: [{}]", testSuite);
        return testSuite;
    }

    public static CreateUpdateTestSuiteRequest updateTestSuiteRq(CreateUpdateTestSuiteRequest source) {
        CreateUpdateTestSuiteRequest testSuite = CreateUpdateTestSuiteRequest.builder()
                .title(source.getTitle() + " Updated")
                .description(source.getDescription() + " Updated")
                .preconditions(source.getPreconditions() + " Updated")
                .parentId(source.getParentId())
                .build();
        log.info("Generated updated test suite: [{}]", testSuite);
        return testSuite;
    }

    public static CreateUpdateTestSuiteRequest invalidUpdateTestSuiteRq(CreateUpdateTestSuiteRequest source) {
        CreateUpdateTestSuiteRequest testSuite = CreateUpdateTestSuiteRequest.builder()
                .title(source.getTitle() + " Updated")
                .description(source.getDescription() + " Updated")
                .preconditions(source.getPreconditions() + " Updated")
                .parentId(999999999)
                .build();
        log.info("Generated invalid updated test suite: [{}]", testSuite);
        return testSuite;
    }

    public static DeleteTestSuiteRequest invalidDeleteTestSuiteRq() {
        DeleteTestSuiteRequest testSuite = DeleteTestSuiteRequest.builder()
                .destinationId(999999999)
                .build();
        log.info("Deleted test suite with non-existent destination Id: [{}]", testSuite);
        return testSuite;
    }
}
