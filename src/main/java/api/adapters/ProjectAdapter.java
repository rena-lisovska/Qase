package api.adapters;

import api.client.ApiClient;
import api.endpoints.Endpoints;
import api.models.projects.request.CreateProjectRequest;
import api.models.projects.response.CreateProjectResponse;
import api.models.projects.response.ErrorCreateProjectResponse;
import api.models.projects.response.DeleteProjectResponse;
import api.models.projects.response.GetAllProjectsResponse;
import api.models.projects.response.GetProjectResponse;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class ProjectAdapter extends BaseAdapter {

    @Step("API: Get project by code")
    public static GetProjectResponse getProject(String code) {
        log.info("Getting project by code [{}] through API", code);
        return ApiClient
                .get(
                        Endpoints.PROJECT_BY_CODE,
                        Map.of("code", code)
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_project_by_code.json"
                ))
                .extract()
                .as(GetProjectResponse.class);
    }

    @Step("API: Create valid project")
    public static CreateProjectResponse createProject(CreateProjectRequest bodyRequest) {
        log.info("Creating valid project through API");
        return ApiClient
                .post(
                        Endpoints.PROJECT,
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/create_project_schema.json"
                ))
                .extract()
                .as(CreateProjectResponse.class);
    }

    @Step("API: Create invalid project")
    public static ErrorCreateProjectResponse createInvalidProject(CreateProjectRequest bodyRequest) {
        log.info("Creating invalid project through API");
        return ApiClient
                .post(
                        Endpoints.PROJECT,
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(badRequest400)
                .extract()
                .as(ErrorCreateProjectResponse.class);
    }

    @Step("API: Delete project by code")
    public static DeleteProjectResponse deleteProject(String code) {
        log.info("Deleting a project through API by code [{}]", code);
        return ApiClient
                .delete(
                        Endpoints.PROJECT_BY_CODE,
                        Map.of("code", code)
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/delete_project_schema.json"
                ))
                .extract()
                .as(DeleteProjectResponse.class);
    }

    @Step("API: Get all projects available to the account")
    public static GetAllProjectsResponse getAllProjects() {
        log.info("Getting all projects through API");
        return ApiClient
                .get(Endpoints.PROJECT)
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_all_projects_schema.json"
                ))
                .extract()
                .as(GetAllProjectsResponse.class);
    }
}
