package api.adapters;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.PropertyReader;

public class BaseAdapter {

    public static final String TOKEN = System.getProperty("qase.token", PropertyReader.getProperty("qase.token"));

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(PropertyReader.getProperty("qase.baseUri"))
            .setBasePath(PropertyReader.getProperty("qase.basePath"))
            .setContentType(ContentType.JSON)
            .addHeader("Token", TOKEN)
            .addFilter(new AllureRestAssured())
            .build();

    public static ResponseSpecification ok200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
