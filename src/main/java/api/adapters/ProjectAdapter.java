package api.adapters;

import api.endpoints.Endpoints;
import api.models.project.request.CreateProjectRequest;
import api.models.project.response.CreateProjectResponse;
import api.models.project.response.GetAllProjectsResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProjectAdapter extends BaseAdapter {

    public static GetAllProjectsResponse getAllProjects() {
        return given()
                .spec(spec)
                .log().all()
                .when()
                .get(Endpoints.PROJECT)
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath("schemas/get_all_projects_schema.json"))
                .extract()
                .as(GetAllProjectsResponse.class);
    }

    public static CreateProjectResponse createProject(CreateProjectRequest request) {
        return given()
                .spec(spec)
                .body(request)
                .log().all()
                .when()
                .post(Endpoints.PROJECT)
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath("schemas/create_project_schema.json"))
                .extract()
                .as(CreateProjectResponse.class);
    }

    public static void deleteProject(String code) {
        given()
                .spec(spec)
                .log().all()
                .when()
                .delete(Endpoints.PROJECT_BY_CODE, code)
                .then()
                .log().all()
                .spec(ok200);
    }
}
