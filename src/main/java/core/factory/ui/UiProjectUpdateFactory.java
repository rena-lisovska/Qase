package core.factory.ui;

import com.github.javafaker.Faker;
import ui.dto.Project;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UiProjectUpdateFactory {

    private static final Faker FAKER = new Faker();

    public static Project updateAllFields() {
        Project project = Project.builder()
                .name(FAKER.company().name())
                .code(FAKER.bothify("UP###"))
                .description(FAKER.lorem().sentence())
                .build();
        log.info("Generated project data for full update: [{}]", project);
        return project;
    }

    public static Project appendUpdate(Project source) {
        Project project = Project.builder()
                .name("Update" + source.getName())
                .code("Up" + source.getCode())
                .description("Update! " + source.getDescription())
                .build();
        log.info("Generated project data with Update suffix: [{}]", project);
        return project;
    }

    public static Project clearProjectName(Project source) {
        Project project = Project.builder()
                .name("")
                .code(source.getCode())
                .description(source.getDescription())
                .build();
        log.info("Generated project with empty name: [{}]", project);
        return project;
    }

    public static Project clearProjectCode(Project source) {
        Project project = Project.builder()
                .name(source.getName())
                .code("")
                .description(source.getDescription())
                .build();
        log.info("Generated project with empty code: [{}]", project);
        return project;
    }
}
