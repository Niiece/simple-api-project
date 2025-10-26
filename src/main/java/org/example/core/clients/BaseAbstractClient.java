package org.example.core.clients;

import static io.restassured.RestAssured.given;
import static org.example.enums.AuthType.NONE;
import static org.example.enums.AuthType.USER_TOKEN;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.enums.AuthType;
import org.example.managers.TokenManager;

public abstract class BaseAbstractClient {
    private final String host;
    private final String basePath;

    public BaseAbstractClient(String host, String basePath) {
        this.basePath = basePath;
        this.host = host;
    }

    private RequestSpecification getBaseSpec(AuthType authType) {
        RequestSpecification spec = given()
            .baseUri(host)
            .header("Content-Type", "application/json")
            .basePath(basePath);

        if (authType == USER_TOKEN) {
            spec.auth().oauth2(TokenManager.getToken());
        }

        return spec;
    }

    protected <T> Response post(String path, T body) {
        return getBaseSpec(USER_TOKEN)
            .when()
            .body(body)
            .post(path)
            .then().extract().response();
    }

    protected <T> Response authPost(String path, T body) {
        return getBaseSpec(NONE)
            .when()
            .body(body)
            .post(path)
            .then().extract().response();
    }

    protected Response get(String path, Object... params) {
        return getBaseSpec(USER_TOKEN)
            .get(path, params)
            .then().extract().response();
    }

    protected Response delete(String path, Object... params) {
        return getBaseSpec(USER_TOKEN)
            .delete(path, params)
            .then().extract().response();
    }
}
