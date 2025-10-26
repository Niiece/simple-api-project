package org.example.core.clients.auth;

import io.restassured.response.Response;

public interface AuthClient {
    Response getToken(String email, String password);
}
