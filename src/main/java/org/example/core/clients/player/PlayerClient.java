package org.example.core.clients.player;

import io.restassured.response.Response;
import org.example.dto.Player;

public interface PlayerClient {
    Response getAllPlayers();
    Response createPlayer(Player player);
    Response deletePlayer(String id);
    Response getOnePlayer(String email);
}
