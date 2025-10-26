package org.example.core.clients.auth;

import static org.example.managers.ConfigManager.getConfig;

import io.restassured.response.Response;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.example.core.clients.BaseAbstractClient;

@Slf4j
public class AuthClientImpl extends BaseAbstractClient implements AuthClient {

    public AuthClientImpl() {
        super(getConfig().host(), getConfig().authBasePath());
    }

    public Response getToken(String email, String password) {
        Map<String, String> body = Map.of(
            "email" , email,
            "password", password);

        log.info("Fetching new token...");
        return authPost("/login", body);
    }
}
