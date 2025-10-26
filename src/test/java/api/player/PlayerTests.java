package api.player;

import static org.example.core.services.PlayerService.getPlayerService;

import api.TestBase;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.example.dto.Player;
import org.example.factory.DataFactory;
import org.testng.annotations.Test;

public class PlayerTests extends TestBase {

    @Test
    public void validateCreatePlayerTest() {
        Comparator<Player> BY_NAME = Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER);

        List<Player> newlyCreatedPlayers = getPlayerService().createPlayersBatch(12);

        List<Player> allCreatedPlayers = getPlayerService().getAllPlayers();
        allCreatedPlayers.sort(BY_NAME);

        Assertions.assertThat(allCreatedPlayers)
            .withFailMessage("Some newly created records of players are not exist or corrupted")
            .containsAll(newlyCreatedPlayers);
    }

    @Test
    public void validateGetPlayerByEmailTest() {
        Player playerToCreate = DataFactory.generateRandomPlayer();
        getPlayerService().createPlayer(playerToCreate);

        Player createdPlayer = getPlayerService().getPlayerByEmail(playerToCreate.getEmail());

        Assertions.assertThat(createdPlayer)
            .withFailMessage("Found player object doesn't match expected")
            .isEqualTo(playerToCreate);
    }

    @Test
    public void validateSortingPlayersByNameTest() {
        Comparator<Player> BY_NAME = Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER);

        getPlayerService().createPlayersBatch(12);
        List<Player> allCreatedPlayers = getPlayerService().getAllPlayers();
        allCreatedPlayers.sort(BY_NAME);

        List<String> allSortedNames = allCreatedPlayers.stream().map(Player::getName).collect(Collectors.toList());

        Assertions.assertThat(allSortedNames)
            .withFailMessage("Sorting by name hasn't been applied")
            .isSorted();
    }

    @Test
    public void validateDeleteAllUsersTest() {
        Player newPlayer = getPlayerService().createPlayer(DataFactory.generateRandomPlayer());
        newPlayer = getPlayerService().getPlayerByEmail(newPlayer.getEmail());

        getPlayerService().deletePlayer(newPlayer);
        List<Player> allCreatedPlayers = getPlayerService().getAllPlayers();

        Assertions.assertThat(allCreatedPlayers)
            .withFailMessage("Found player object doesn't match expected")
            .doesNotContain(newPlayer);
    }

}
