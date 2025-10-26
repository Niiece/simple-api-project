package org.example.factory;

import com.github.javafaker.Faker;
import org.example.dto.Player;

public class DataFactory {

    private static final Faker faker = new Faker();

    public static Player generateRandomPlayer() {
        return Player.builder()
            .id(String.valueOf(faker.number().digits(5)))
            .name(faker.name().firstName())
            .surname(faker.name().lastName())
            .username(faker.name().username())
            .email(faker.internet().emailAddress())
            .build();
    }

}
