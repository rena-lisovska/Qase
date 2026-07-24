package core.factory.ui;

import com.github.javafaker.Faker;
import core.dto.Project;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UiProjectFactory {

    private static final Faker FAKER = new Faker();

    public static Project validProject() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .description(FAKER.lorem().sentence())
                .accessType("private")
                .memberAccess("all")
                .build();
        log.info("Generated UI project with all fields: [{}]", project);
        return project;
    }

    public static Project minimalProject() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .build();
        log.info("Generated UI project with required fields only: [{}]", project);
        return project;
    }

    public static Project withoutName() {
        Project project = Project.builder()
                .code(FAKER.bothify("QA##"))
                .accessType("private")
                .memberAccess("all")
                .build();
        log.info("Generated UI project without name: [{}]", project);
        return project;
    }

    public static Project withoutCode() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .accessType("private")
                .memberAccess("all")
                .build();
        log.info("Generated UI project without code: [{}]", project);
        return project;
    }

    public static Project publicProject() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .description(FAKER.lorem().sentence())
                .accessType("public")
                .build();
        log.info("Generated UI public project: [{}]", project);
        return project;
    }

    public static Project groupAccessProject() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .accessType("private")
                .memberAccess("group")
                .group("Owner group")
                .build();
        log.info("Generated UI project with member access 'Groupe': [{}]", project);
        return project;
    }

    public static Project noMembersProject() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .accessType("private")
                .memberAccess("none")
                .build();
        log.info("Generated UI project with member access 'None': [{}]", project);
        return project;
    }

    public static Project groupAccessWithoutGroup() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("QA##"))
                .accessType("private")
                .memberAccess("group")
                .build();
        log.info("Generated project with group access without group selected: [{}]", project);
        return project;
    }

    public static Project emptyProject() {
        Project project = Project.builder().build();
        log.info("Generated empty UI project: [{}]", project);
        return project;
    }
}
