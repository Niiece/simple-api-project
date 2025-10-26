package org.example.core.clients.player;

import static org.example.managers.ConfigManager.getConfig;

import io.restassured.response.Response;
import java.util.Map;
import org.example.core.clients.BaseAbstractClient;
import org.example.dto.Player;

public class PlayerClientImpl extends BaseAbstractClient implements PlayerClient{

    public PlayerClientImpl() {
        super(getConfig().host(), getConfig().basePath());
    }

    public Response getAllPlayers() {
        return get("/getAll");
    }

    public Response createPlayer(Player player) {
        return post("/create", player);
    }

    public Response deletePlayer(String id) {
        return delete("/deleteOne/{id}", id);
    }

    public Response getOnePlayer(String email) {
        Map<String, Object> body = Map.of("email", email);
        return post("/getOne", body);
    }
}
