package api.adapters;

import api.models.project.CreateProjectRequest;
import api.models.project.CreateProjectResponse;
import api.models.project.GetAllProjectsResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProjectAdapter extends BaseAdapter {

    public static GetAllProjectsResponse getAllProjects() {
        return given()
                .spec(spec)
                .log().all()
                .when()
                .get("/project")
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath("schemas/get_all_projects_schema.json"))
                .extract()
                .as(GetAllProjectsResponse.class);
    }

    public static CreateProjectResponse сreateProject(CreateProjectRequest request) {
        return given()
                .spec(spec)
                .body(request)
                .log().all()
                .when()
                .post("/project")
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
                .pathParam("code", code)
                .log().all()
                .when()
                .delete("/project/{code}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
