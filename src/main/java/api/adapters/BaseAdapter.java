package api.adapters;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import core.utils.PropertyReader;

public class BaseAdapter {

    public static final String TOKEN = System.getProperty("API.token", PropertyReader.getProperty("API.token"));

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(PropertyReader.getProperty("API.baseUri"))
            .setBasePath(PropertyReader.getProperty("API.basePath"))
            .setContentType(ContentType.JSON)
            .addHeader("Token", TOKEN)
            .addFilter(new AllureRestAssured())
            .build();

    public static ResponseSpecification ok200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification badRequest400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification notFound404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();

    public static ResponseSpecification UnprocessableEntity422 = new ResponseSpecBuilder()
            .expectStatusCode(422)
            .build();
}
