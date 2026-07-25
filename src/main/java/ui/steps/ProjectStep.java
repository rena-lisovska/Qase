package ui.steps;

import core.dto.Project;
import io.qameta.allure.Step;
import ui.pages.CreateProjectModal;
import ui.pages.ProjectPage;
import ui.pages.ProjectsPage;

public class ProjectStep {

    private final ProjectsPage projectsPage = new ProjectsPage();
    private final CreateProjectModal createProjectModal = new CreateProjectModal();

    @Step("Create new project")
    public ProjectPage createProject(Project project) {
        projectsPage.clickCreateProject();
        createProjectModal
                .isModalOpened()
                .fill(project)
                .clickCreate();
        return new ProjectPage().isPageOpened(project.getCode());
    }

    @Step("Cancel project creation")
    public ProjectsPage cancelProjectCreation(Project project) {
        projectsPage.clickCreateProject();
        createProjectModal
                .isModalOpened()
                .fill(project)
                .clickCancel();
        return new ProjectsPage().isPageOpened();
    }
}
