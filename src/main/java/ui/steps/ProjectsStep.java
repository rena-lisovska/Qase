package ui.steps;

import ui.dto.Project;
import io.qameta.allure.Step;
import ui.pages.CreateProjectModal;
import ui.pages.ProjectPage;
import ui.pages.ProjectSettingsPage;
import ui.pages.ProjectsPage;

public class ProjectsStep {

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

    @Step("Create project and open project settings")
    public ProjectSettingsPage createProjectAndOpenSettings(Project project) {
        createProject(project);
        return openProjectSettings(project);
    }

    @Step("Open project settings")
    public ProjectSettingsPage openProjectSettings(Project project) {
        return projectsPage
                .openPage()
                .openProjectSettings(project.getName())
                .isPageOpened(project.getCode());
    }
}
