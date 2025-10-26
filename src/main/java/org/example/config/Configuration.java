package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources({
    "classpath:configuration.properties"
})
public interface Configuration extends Config {
    @Key("host")
    String host();

    @Key("basePath")
    String basePath();

    @Key("authBasePath")
    String authBasePath();

    @Key("userEmail")
    String userEmail();

    @Key("userPassword")
    String userPassword();

    @Key("tokenExpirationTimeout")
    @DefaultValue("3600")
    int tokenExpirationTimeout();
}
