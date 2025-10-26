package org.example.core.services;

import static org.example.utils.JsonUtils.getArrayType;

import io.restassured.response.Response;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseAbstractService {

    protected void assertStatus(Response response, int expectedStatus) {
        int actualStatus = response.statusCode();
        if (actualStatus != expectedStatus) {
            log.error("Expected status {}, but got {}. Body: {}", expectedStatus, actualStatus, response.asPrettyString());
            throw new AssertionError("Unexpected status: " + actualStatus);
        }
    }

    protected <T> T extractObject(Response response, Class<T> clazz) {
        return response.then().extract().as(clazz);
    }

    protected <T> List<T> extractList(Response response, Class<T> clazz) {
        return response.as(getArrayType(clazz));
    }
}
