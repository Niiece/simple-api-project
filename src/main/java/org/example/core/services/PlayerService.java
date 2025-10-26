package org.example.core.services;

import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.core.clients.player.PlayerClient;
import org.example.core.clients.player.PlayerClientImpl;
import org.example.dto.Player;
import org.example.factory.DataFactory;

@Slf4j
public class PlayerService extends BaseAbstractService {
    private static PlayerService INSTANCE;
    private final PlayerClient playerClient = new PlayerClientImpl();

    private PlayerService() {}

    public static PlayerService getPlayerService() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerService();
        }
        return INSTANCE;
    }

    public List<Player> getAllPlayers() {
        log.info("Sending request to get all players data...");
        Response response = playerClient.getAllPlayers();
        log.info("----- RESPONSE BODY -----");
        log.info(response.body().prettyPrint());
        assertStatus(response, 200);
        return extractList(response, Player.class);
    }

    public Player createPlayer(Player player) {
        log.info("Sending request to CREATE A PLAYER...");
        Response response = playerClient.createPlayer(player);
        log.info("----- RESPONSE BODY -----");
        log.info(response.body().prettyPrint());
        assertStatus(response, 201);
        log.info("----- END OF BODY. PLAYERS CREATED -----");
        return extractObject(response, Player.class);
    }

    public Player deletePlayer(Player player) {
        return deletePlayer(player.getId());
    }

    public Player deletePlayer(String id) {
        Response response = playerClient.deletePlayer(id);
        assertStatus(response, 200);
        return extractObject(response, Player.class);
    }

    public Player getPlayerByEmail(String email) {
        Response response = playerClient.getOnePlayer(email);
        assertStatus(response, 201);
        return extractObject(response, Player.class);
    }

    public List<Player> createPlayersBatch(int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Player randomPlayer = DataFactory.generateRandomPlayer();
            Player created = createPlayer(randomPlayer);
            players.add(created);
        }
        return players;
    }


}
