package api.adapters;

import api.client.ApiClient;
import api.endpoints.ApiEndpoints;
import api.models.testcase.request.CreateTestCaseRequest;
import api.models.testcase.request.UpdateTestCaseRequest;
import api.models.testcase.response.*;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class TestCaseAdapter extends BaseAdapter {

    @Step("API: Get specific test case by id")
    public static GetSpecificTestCaseResponse getSpecificTestCase(String code, int id) {
        log.info("Getting specific test case through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .get(
                        ApiEndpoints.CASE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        )
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_specific_test_case_schema.json"
                ))
                .extract()
                .as(GetSpecificTestCaseResponse.class);
    }

    @Step("API: Create valid test case")
    public static CRUDTestCaseResponse createTestCase(String code, CreateTestCaseRequest bodyRequest) {
        log.info("Creating valid test case through API");
        return ApiClient
                .post(
                        ApiEndpoints.CASE_BY_CODE,
                        Map.of("code", code),
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/create_test_case_schema.json"
                ))
                .extract()
                .as(CRUDTestCaseResponse.class);
    }

    @Step("API: Update test case by id")
    public static CRUDTestCaseResponse updateTestCase(String code, int id, UpdateTestCaseRequest bodyRequest) {
        log.info("Update test case through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .patch(
                        ApiEndpoints.CASE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        ),
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/update_test_case_schema.json"
                ))
                .extract()
                .as(CRUDTestCaseResponse.class);
    }

    @Step("API: Delete test case by id")
    public static DeleteTestCaseResponse deleteTestCase(String code, int id) {
        log.info("Deleting test case through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .delete(
                        ApiEndpoints.CASE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        )
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/delete_test_case_schema.json"
                ))
                .extract()
                .as(DeleteTestCaseResponse.class);
    }

    @Step("API: Verify deleted test case returns 404 response")
    public static DeleteErrorTestCaseResponse verifyDeletedTestCaseResponse(String projectCode, Integer id) {
        log.info("Verify deleted test case with id [{}] returns 404", id);
        return ApiClient
                .get(
                        ApiEndpoints.CASE_BY_CODE_ID,
                        Map.of(
                                "code", projectCode,
                                "id", id
                        )
                )
                .then()
                .spec(notFound404)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/delete_error_test_case_schema.json"
                ))
                .extract()
                .as(DeleteErrorTestCaseResponse.class);
    }

    @Step("API: Get all test case by project")
    public static GetAllCasesResponse getAllTestCase(String code) {
        log.info("Getting all test case through API by project's code [{}]", code);
        return ApiClient
                .get(
                        ApiEndpoints.CASE_BY_CODE,
                        Map.of("code", code)
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_all_test_case_schema.json"
                ))
                .extract()
                .as(GetAllCasesResponse.class);
    }
}
