package api.client;

import api.adapters.BaseAdapter;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ApiClient extends BaseAdapter {

    public static Response get(String endpoint) {
        return given()
                .spec(spec)
                .log().all()
        .when()
                .get(endpoint);
    }

    public static Response get(String endpoint,
                               Map<String, ?> pathParams) {
        return given()
                .spec(spec)
                .pathParams(pathParams)
                .log().all()
        .when()
                .get(endpoint);
    }

    public static Response post(String endpoint,
                                Object body) {
        return given()
                .spec(spec)
                .body(body)
                .log().all()
        .when()
                .post(endpoint);
    }

    public static Response post(String endpoint,
                                Object body,
                                Map<String, ?> pathParams) {
        return given()
                .spec(spec)
                .pathParams(pathParams)
                .body(body)
                .log().all()
        .when()
                .post(endpoint);
    }

    public static Response patch(String endpoint,
                                 Object body) {
        return given()
                .spec(spec)
                .body(body)
                .log().all()
        .when()
                .patch(endpoint);
    }

    public static Response patch(String endpoint,
                                 Object body,
                                 Map<String, ?> pathParams) {
        return given()
                .spec(spec)
                .pathParams(pathParams)
                .body(body)
                .log().all()
        .when()
                .patch(endpoint);
    }

    public static Response delete(String endpoint, Map<String, ?> pathParams) {
        return given()
                .spec(spec)
                .pathParams(pathParams)
                .log().all()
        .when()
                .delete(endpoint);
    }
}
