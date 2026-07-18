package api.adapters;

import api.client.ApiClient;
import api.endpoints.Endpoints;
import api.models.testsuite.request.CreateUpdateTestSuiteRequest;
import api.models.testsuite.response.*;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class TestSuiteAdapter extends BaseAdapter {

    @Step("API: Get specific test suite by id")
    public static GetSpecificTestSuiteResponse getSpecificTestSuite(String code, int id) {
        log.info("Getting specific test suite through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .get(
                        Endpoints.SUITE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        )
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_specific_test_suite_schema.json"
                ))
                .extract()
                .as(GetSpecificTestSuiteResponse.class);
    }

    @Step("API: Create valid test suite")
    public static CRUDTestSuiteResponse createTestSuite(String code, CreateUpdateTestSuiteRequest bodyRequest) {
        log.info("Creating valid test suite through API");
        return ApiClient
                .post(
                        Endpoints.SUITE_BY_CODE,
                        Map.of("code", code),
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/create_test_suite_schema.json"
                ))
                .extract()
                .as(CRUDTestSuiteResponse.class);
    }

    @Step("API: Update test suite by id")
    public static CRUDTestSuiteResponse updateTestSuite(String code, int id, CreateUpdateTestSuiteRequest bodyRequest) {
        log.info("Update test suite through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .patch(
                        Endpoints.SUITE_BY_CODE_ID,
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
                        "schemas/update_test_suite_schema.json"
                ))
                .extract()
                .as(CRUDTestSuiteResponse.class);
    }

    @Step("API: Delete test suite by id")
    public static CRUDTestSuiteResponse deleteTestSuite(String code, int id) {
        log.info("Deleting test suite through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .delete(
                        Endpoints.SUITE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        )
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/delete_test_suite_schema.json"
                ))
                .extract()
                .as(CRUDTestSuiteResponse.class);
    }

    @Step("API: Get all test suite by project")
    public static GetAllTestSuiteResponse getAllTestSuite(String code) {
        log.info("Getting all test suite through API by project's code [{}]", code);
        return ApiClient
                .get(
                        Endpoints.SUITE_BY_CODE,
                        Map.of("code", code)
                )
                .then()
                .log().all()
                .spec(ok200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_all_test_suite_schema.json"
                ))
                .extract()
                .as(GetAllTestSuiteResponse.class);
    }

    @Step("API: Create invalid test suite without title")
    public static ErrorCreateTestSuiteResponse createInvalidTestSuite(String code, CreateUpdateTestSuiteRequest bodyRequest){
        log.info("Creating invalid test suite without title through API");
        return ApiClient
                .post(
                Endpoints.SUITE_BY_CODE,
                        Map.of("code", code),
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(badRequest400)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/create_error_test_suite_schema.json"
                ))
                .extract()
                .as(ErrorCreateTestSuiteResponse.class);
    }

    @Step("API: Update test suite with an incorrect parent_id")
    public static ErrorUpdateTestSuiteResponse incorrectUpdateTestSuite(String code, int id, CreateUpdateTestSuiteRequest bodyRequest){
        log.info("Incorrect update test suite through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .patch(
                        Endpoints.SUITE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        ),
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(badRequest400)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/update_error_test_suite_schema.json"
                ))
                .extract()
                .as(ErrorUpdateTestSuiteResponse.class);
    }

    @Step("API: Get specific test suite by non-existent id")
    public static ErrorGetSpecificTestSuiteResponse incorrectGetTestSuite(String code, int id){
        log.info("Getting non-existent test suite through API by id [{}] from project's code [{}]", id, code);
        return ApiClient
                .get(
                        Endpoints.SUITE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        )
                )
                .then()
                .log().all()
                .spec(badRequest400)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/get_error_specific_test_suite.json"
                ))
                .extract()
                .as(ErrorGetSpecificTestSuiteResponse.class);
    }

    @Step("API: Delete test suite and move its cases to non-existent suite")
    public static CRUDTestSuiteResponse incorrectDeleteTestSuite(String code, int id) {
        log.info("Deleting test suite by id [{}] and moving its cases to existing test suite", id);
        return ApiClient
                .delete(
                        Endpoints.SUITE_BY_CODE_ID,
                        Map.of(
                                "code", code,
                                "id", id
                        )
                )
                .then()
                .log().all()
                .spec(badRequest400)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/delete_error_test_suite_schema.json"
                ))
                .extract()
                .as(CRUDTestSuiteResponse.class);
    }
}
