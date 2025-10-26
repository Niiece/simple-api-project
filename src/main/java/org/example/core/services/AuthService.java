package org.example.core.services;

import io.restassured.response.Response;
import org.example.core.clients.auth.AuthClient;
import org.example.core.clients.auth.AuthClientImpl;

public class AuthService extends BaseAbstractService {
    private static AuthService INSTANCE;
    private static final AuthClient authClient = new AuthClientImpl();

    private AuthService() {}

    public static AuthService getAuthService() {
        if (INSTANCE == null) {
            INSTANCE = new AuthService();
        }
        return INSTANCE;
    }

    public synchronized String fetchNewToken(String email, String password) {
        Response response = authClient.getToken(email, password);

        assertStatus(response, 201);
        String token = response.jsonPath().getString("accessToken");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not returned in response");
        }
        return token;
    }
}
