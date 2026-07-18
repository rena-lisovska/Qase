package tests.api;

import api.adapters.ProjectAdapter;
import api.adapters.TestSuiteAdapter;
import api.models.testsuite.request.CreateUpdateTestSuiteRequest;
import api.models.testsuite.request.DeleteTestSuiteRequest;
import api.models.testsuite.response.*;
import factory.EntityFactory;
import factory.TestSuiteFactory;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SuiteApiTest {

    private String projectCode;

    @BeforeMethod
    public void setUpProject() {
        projectCode = EntityFactory.createProjectCode();
    }

    @Test(
            testName = "Create, get and delete test suite with valid data",
            description = "Verifies that user can: create, get, update and delete test suite",
            groups = {"regression", "api"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Qase_01")
    @Feature("TestSuite")
    @Story("CRUD operations on test suite with all fields")
    public void checkCRUDTestSuite() {
        SoftAssert softAssert = new SoftAssert();
        CreateUpdateTestSuiteRequest createRequest = TestSuiteFactory.validTestSuiteRq();
        CRUDTestSuiteResponse createResponse = TestSuiteAdapter.createTestSuite(projectCode, createRequest);
        softAssert.assertTrue(
                createResponse.getStatus(),
                "The test suite create status is not true"
        );
        softAssert.assertNotNull(
                createResponse.getResult().getId(),
                "ID is missing or generated incorrectly"
        );
        Integer testSuiteId = createResponse.getResult().getId();
        GetSpecificTestSuiteResponse createGetResponse = TestSuiteAdapter.getSpecificTestSuite(projectCode, testSuiteId);
        softAssert.assertEquals(
                createGetResponse.getResult().getId(),
                testSuiteId,
                "ID is missing or does not match"
        );
        softAssert.assertEquals(
                createGetResponse.getResult().getTitle(),
                createRequest.getTitle(),
                "Title does not match"
        );
        softAssert.assertEquals(
                createGetResponse.getResult().getDescription(),
                createRequest.getDescription(),
                "Description does not match"
        );
        softAssert.assertEquals(
                createGetResponse.getResult().getPreconditions(),
                createRequest.getPreconditions(),
                "Preconditions does not match"
        );
        CreateUpdateTestSuiteRequest updateRequest = TestSuiteFactory.updateTestSuiteRq(createRequest);
        CRUDTestSuiteResponse updateResponse = TestSuiteAdapter.updateTestSuite(projectCode, testSuiteId, updateRequest);
        softAssert.assertTrue(
                updateResponse.getStatus(),
                "The test suite update status is not true"
        );
        GetSpecificTestSuiteResponse updateGetResponse = TestSuiteAdapter.getSpecificTestSuite(projectCode, testSuiteId);
        softAssert.assertEquals(
                updateGetResponse.getResult().getId(),
                testSuiteId,
                "ID is missing or does not match"
        );
        softAssert.assertEquals(
                updateGetResponse.getResult().getTitle(),
                updateRequest.getTitle(),
                "Updated Title does not match"
        );
        softAssert.assertEquals(
                updateGetResponse.getResult().getDescription(),
                updateRequest.getDescription(),
                "Updated Description does not match"
        );
        softAssert.assertEquals(
                updateGetResponse.getResult().getPreconditions(),
                updateRequest.getPreconditions(),
                "Updated Preconditions does not match"
        );
        CRUDTestSuiteResponse deleteResponse = TestSuiteAdapter.deleteTestSuite(projectCode, testSuiteId);
        softAssert.assertTrue(
                deleteResponse.getStatus(),
                "Failed to delete test suite with id: " + testSuiteId
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Create test suite without title",
            description = "Verifies that user can't create test suite without title",
            groups = {"regression", "api"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Qase_01")
    @Feature("TestSuite")
    @Story("Create test suite without title")
    public void checkCreateTestSuiteWithoutTitle() {
        SoftAssert softAssert = new SoftAssert();
        CreateUpdateTestSuiteRequest createRequest = TestSuiteFactory.invalidTestSuiteRq();
        ErrorCreateTestSuiteResponse createResponse = TestSuiteAdapter.createInvalidTestSuite(projectCode, createRequest);
        softAssert.assertFalse(
                createResponse.getStatus(),
                "The test suite status is not false"
        );
        ErrorField titleError = createResponse.getErrorFields()
                .stream()
                .filter(error -> error.getField().equals("title"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Title validation error was not found"));
        softAssert.assertEquals(
                titleError.getError(),
                "The title field is required.",
                "Unexpected title validation message"
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Get test suite by non-existent id",
            description = "Verifies that user can't get test suite by non-existent id",
            groups = {"regression", "api"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.MINOR)
    @Epic("Qase_01")
    @Feature("TestSuite")
    @Story("Get test suite by non-existent id")
    public void checkGetTestSuiteByNonExistentId() {
        SoftAssert softAssert = new SoftAssert();
        int nonExistentId = 999999;
        ErrorGetSpecificTestSuiteResponse getResponse = TestSuiteAdapter.incorrectGetTestSuite(projectCode, nonExistentId);
        softAssert.assertFalse(
                getResponse.getStatus(),
                "GET request for non-existent test suite should return status=false"
        );
        softAssert.assertEquals(
                getResponse.getErrorMessage(),
                "Suite not found",
                "Unexpected error message for non-existent test suite ID: " + nonExistentId
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Update test suite with incorrect parameter 'Parent Id'",
            description = "Verifies that user can's update test suite with incorrect parameter 'Parent Id'",
            groups = {"regression", "api"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.MINOR)
    @Epic("Qase_01")
    @Feature("TestSuite")
    @Story("Update test suite with incorrect parameter 'Parent Id'")
    public void checkUpdateTestSuiteWithIncorrectData() {
        SoftAssert softAssert = new SoftAssert();
        CreateUpdateTestSuiteRequest createRequest = TestSuiteFactory.validTestSuiteRq();
        CRUDTestSuiteResponse createResponse = TestSuiteAdapter.createTestSuite(projectCode, createRequest);
        softAssert.assertNotNull(
                createResponse.getResult().getId(),
                "ID is missing or generated incorrectly"
        );
        Integer testSuiteId = createResponse.getResult().getId();
        CreateUpdateTestSuiteRequest updateRequest = TestSuiteFactory.invalidUpdateTestSuiteRq(createRequest);
        ErrorUpdateTestSuiteResponse updateResponse = TestSuiteAdapter.incorrectUpdateTestSuite(projectCode, testSuiteId, updateRequest);
        ErrorField titleError = updateResponse.getErrorFields()
                .stream()
                .filter(error -> error.getField().equals("parent_id"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Parent Id validation error was not found"));
        softAssert.assertEquals(
                titleError.getError(),
                "Parent suite not found",
                "Unexpected Parent Id validation message"
        );
        softAssert.assertAll();
    }

    @Test(
            testName = "Delete test suite with incorrect parameter 'Destination Id'",
            description = "Verifies that user can's delete test suite with incorrect parameter 'Destination Id'",
            groups = {"regression", "api"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.MINOR)
    @Epic("Qase_01")
    @Feature("TestSuite")
    @Story("Delete test suite with incorrect parameter 'Destination Id''")
    public void checkDeleteTestSuiteWithNonExistingDestinationId() {
        SoftAssert softAssert = new SoftAssert();
        CreateUpdateTestSuiteRequest createRequest = TestSuiteFactory.validTestSuiteRq();
        CRUDTestSuiteResponse createResponse = TestSuiteAdapter.createTestSuite(projectCode, createRequest);
        softAssert.assertNotNull(
                createResponse.getResult().getId(),
                "ID is missing or generated incorrectly"
        );
        Integer testSuiteId = createResponse.getResult().getId();
        DeleteTestSuiteRequest deleteRequest = TestSuiteFactory.invalidDeleteTestSuiteRq();
        ErrorDeleteTestSuiteResponse deleteResponse = TestSuiteAdapter.incorrectDeleteTestSuite(projectCode, testSuiteId, deleteRequest);
        softAssert.assertFalse(
                deleteResponse.getStatus(),
                "Delete operation should fail"
        );
        softAssert.assertEquals(
                deleteResponse.getErrorMessage(),
                "Destination id is not valid",
                "Unexpected error message"
        );
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDownProject() {
        if (projectCode != null) {
            ProjectAdapter.deleteProject(projectCode);
        }
    }
}
