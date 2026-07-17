package tests.api;

import api.adapters.ProjectAdapter;
import api.adapters.TestCaseAdapter;
import api.models.testcase.request.CreateTestCaseRequest;
import api.models.testcase.request.UpdateTestCaseRequest;
import api.models.testcase.response.CRUDTestCaseResponse;
import api.models.testcase.response.DeleteErrorTestCaseResponse;
import api.models.testcase.response.DeleteTestCaseResponse;
import api.models.testcase.response.GetSpecificTestCaseResponse;
import factory.TestCaseFactory;
import factory.EntityFactory;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CaseApiTest {

    private String projectCode;

    @BeforeMethod
    public void setUpProject() {
        projectCode = EntityFactory.createProjectCode();
    }

    @Test(
            testName = "Create, get and delete test case with valid data",
            description = "Verifies that user can: create, get, update and delete test case",
            groups = {"regression", "api"}
    )
    @Owner("AQA Team, Lisovskaya I.")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Qase_01")
    @Feature("TestCase")
    @Story("CRUD operations on test case with all fields")
    public void checkCRUDTestCase() {
        SoftAssert softAssert = new SoftAssert();
        CreateTestCaseRequest createRequest = TestCaseFactory.validTestCaseRq();
        CRUDTestCaseResponse createResponse = TestCaseAdapter.createTestCase(projectCode, createRequest);
        softAssert.assertTrue(
                createResponse.getStatus(),
                "The test case create status is not true"
        );
        softAssert.assertNotNull(
                createResponse.getResult().getId(),
                "ID is missing or generated incorrectly"
        );
        Integer testCaseId = createResponse.getResult().getId();
        GetSpecificTestCaseResponse createGetResponse = TestCaseAdapter.getSpecificTestCase(projectCode, testCaseId);
        softAssert.assertEquals(
                createGetResponse.getResult().getId(),
                testCaseId,
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
                createGetResponse.getResult().getSeverity(),
                createRequest.getSeverity(),
                "The value in Severity does not match"
        );
        UpdateTestCaseRequest updateRequest = TestCaseFactory.updateTestCaseRq(createRequest);
        CRUDTestCaseResponse updateResponse = TestCaseAdapter.updateTestCase(projectCode, testCaseId, updateRequest);
        softAssert.assertTrue(
                updateResponse.getStatus(),
                "The test case update status is not true"
        );
        GetSpecificTestCaseResponse updateGetResponse = TestCaseAdapter.getSpecificTestCase(projectCode, testCaseId);
        softAssert.assertEquals(
                updateGetResponse.getResult().getId(),
                testCaseId,
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
                updateGetResponse.getResult().getSeverity(),
                updateRequest.getSeverity(),
                "Updated Severity does not match"
        );
        DeleteTestCaseResponse deleteResponse = TestCaseAdapter.deleteTestCase(projectCode, testCaseId);
        softAssert.assertTrue(
                deleteResponse.getStatus(),
                "Failed to delete test case with id: " + testCaseId
        );
        softAssert.assertAll();
        DeleteErrorTestCaseResponse deleteErrorResponse = TestCaseAdapter.verifyDeletedTestCaseResponse(projectCode, testCaseId);
        softAssert.assertFalse(
                deleteErrorResponse.getStatus(),
                "Deleted test case status should be false"
        );
        softAssert.assertEquals(
                deleteErrorResponse.getErrorMessage(),
                "TestCase not found",
                "Unexpected error message"
        );
    }

    @AfterMethod
    public void tearDownProject() {
        if (projectCode != null) {
            ProjectAdapter.deleteProject(projectCode);
        }
    }
}
