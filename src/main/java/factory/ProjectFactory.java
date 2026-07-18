package factory;

import api.models.project.request.CreateProjectRequest;
import com.github.javafaker.Faker;
import enums.AccessType;
import enums.GroupType;
import lombok.extern.log4j.Log4j2;
import java.util.Locale;

@Log4j2
public class ProjectFactory {

    private static final Faker FAKER = new Faker(Locale.ENGLISH);

    public static CreateProjectRequest validProjectRq() {
        CreateProjectRequest project = CreateProjectRequest.builder()
                .title(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .description(FAKER.lorem().sentence())
                .access(AccessType.random().getValue())
                .group(GroupType.random().getValue())
                .build();
        log.info("Generated project with all fields: [{}]", project);
        return project;
    }

    public static CreateProjectRequest minimalProjectRq() {
        CreateProjectRequest project = CreateProjectRequest.builder()
                .title(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .access(AccessType.random().getValue())
                .build();
        log.info("Generated project with required fields only: [{}]", project);
        return project;
    }

    public static CreateProjectRequest invalidProjectRq() {
        CreateProjectRequest project = CreateProjectRequest.builder()
                .description(FAKER.lorem().sentence())
                .access(AccessType.random().getValue())
                .group(GroupType.random().getValue())
                .build();
        log.info("Generated project without required fields: [{}]", project);
        return project;
    }

    public static CreateProjectRequest emptyProjectRq() {
        CreateProjectRequest project = CreateProjectRequest.builder()
                .build();
        log.info("Generated empty project");
        return project;
    }
}
