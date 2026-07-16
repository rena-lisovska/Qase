package api.adapters;

import api.client.ApiClient;
import api.endpoints.Endpoints;
import api.models.cases.request.CreateCaseRequest;
import api.models.cases.request.UpdateCaseRequest;
import api.models.cases.response.CRUDCaseResponse;
import api.models.cases.response.DeleteCaseResponse;
import api.models.cases.response.GetAllCasesResponse;
import api.models.cases.response.GetSpecificCaseResponse;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class CaseAdapter extends BaseAdapter {

    @Step("API: Get all test case by project by code")
    public static GetAllCasesResponse getAllTestCase(String code) {
        log.info("Getting all test case  through API by project code [{}]", code);
        return ApiClient
                .get(
                        Endpoints.CASE_BY_CODE,
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

    @Step("API: Get specific test case by id from project code")
    public static GetSpecificCaseResponse getSpecificTestCase(String code, String id) {
        log.info("Getting specific test case  through API by id [{}] from project by code [{}]", id, code);
        return ApiClient
                .get(
                        Endpoints.CASE_BY_CODE_ID,
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
                .as(GetSpecificCaseResponse.class);
    }

    @Step("API: Create valid test case")
    public static CRUDCaseResponse createTestCase(String code, CreateCaseRequest bodyRequest) {
        log.info("Creating valid test case through API");
        return ApiClient
                .post(
                        Endpoints.PROJECT_BY_CODE,
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
                .as(CRUDCaseResponse.class);
    }

    @Step("API: Update test case")
    public static CRUDCaseResponse updateTestCase(String code, String id, UpdateCaseRequest bodyRequest) {
        log.info(" Update test case through API by id [{}] from project by code [{}] ", id, code);
        return ApiClient
                .patch(
                        Endpoints.CASE_BY_CODE_ID,
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
                .as(CRUDCaseResponse.class);
    }

    @Step("API: Delete test case by id")
    public static DeleteCaseResponse deleteTestCase(String code, String id) {
        log.info("Deleting test case through API by id [{}] from project code [{}]", id, code);
        return ApiClient
                .delete(
                        Endpoints.CASE_BY_CODE_ID,
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
                .as(DeleteCaseResponse.class);
    }

    @Step("API: Create invalid test case")
    public static CRUDCaseResponse createInvalidTestCase(String code, CreateCaseRequest bodyRequest) {
        log.info("Creating invalid test case through API");
        return ApiClient
                .post(
                        Endpoints.PROJECT_BY_CODE,
                        Map.of("code", code),
                        bodyRequest
                )
                .then()
                .log().all()
                .spec(UnprocessableEntity422)
                .extract()
                .as(CRUDCaseResponse.class);
    }
}
