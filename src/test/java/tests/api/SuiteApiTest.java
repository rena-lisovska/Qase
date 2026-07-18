package tests.api;

import api.adapters.ProjectAdapter;
import api.adapters.TestSuiteAdapter;
import api.models.testsuite.request.CreateUpdateTestSuiteRequest;
import api.models.testsuite.response.CRUDTestSuiteResponse;
import api.models.testsuite.response.GetSpecificTestSuiteResponse;
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
    }

    @AfterMethod
    public void tearDownProject() {
        if (projectCode != null) {
            ProjectAdapter.deleteProject(projectCode);
        }
    }
}
