package api;

import static org.example.core.services.PlayerService.getPlayerService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.dto.Player;
import org.testng.annotations.AfterClass;

@Slf4j
public abstract class TestBase {

    @AfterClass
    public void cleanUp() {
        log.info("AfterClass operation: Clean up...");
        List<Player> allPlayers = getPlayerService().getAllPlayers();
        allPlayers.parallelStream().forEach(getPlayerService()::deletePlayer);

        Assertions.assertThat(getPlayerService().getAllPlayers().size())
            .withFailMessage("Some Players were not deleted")
            .isEqualTo(0);
        log.info("AfterClass operation: Clean up completed. Deleted {} players", allPlayers.size());
    }
}
