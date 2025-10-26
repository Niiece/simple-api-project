package org.example.managers;

import static org.example.core.services.AuthService.getAuthService;
import static org.example.managers.ConfigManager.getConfig;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenManager {

    private static final ThreadLocal<TokenInfo> threadToken = ThreadLocal.withInitial(() -> null);

    public static String getToken() {
        TokenInfo info = threadToken.get();

        log.info("Getting new token...");
        if (info == null || info.expiry.isBefore(Instant.now())) {
            info = new TokenInfo(
                getAuthService()
                    .fetchNewToken(
                        getConfig().userEmail(),
                        getConfig().userPassword()),
                Instant.now().plusSeconds(getConfig().tokenExpirationTimeout()));
            threadToken.set(info);
        }

        return info.token;
    }

    private static class TokenInfo {
        final String token;
        final Instant expiry;

        TokenInfo(String token, Instant expiry) {
            this.token = token;
            this.expiry = expiry;
        }
    }

}
