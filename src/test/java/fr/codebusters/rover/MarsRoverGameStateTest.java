package fr.codebusters.rover;

import fr.codebusters.rover.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverGameStateTest {

    @Test
    void should_return_final_positions_when_process_game() {
        // GIVEN
        var actions1 = List.of(
                Action.LEFT,
                Action.MOVE,
                Action.LEFT,
                Action.MOVE,
                Action.LEFT,
                Action.MOVE,
                Action.LEFT,
                Action.MOVE,
                Action.MOVE

        );
        var actions2 = List.of(
                Action.MOVE,
                Action.MOVE,
                Action.RIGHT,
                Action.MOVE,
                Action.MOVE,
                Action.RIGHT,
                Action.MOVE,
                Action.RIGHT,
                Action.RIGHT,
                Action.MOVE

        );
        var game = new MarsRoverGameState(new Plateau(5, 5), List.of(
                new NasaMission(new MarsRover(1, 2, Direction.N), actions1),
                new NasaMission(new MarsRover(3, 3, Direction.E), actions2)
        ));
        GameStatus gameStatus = game.processGame();
        String endGameReport = game.endGameReport();
        assertEquals(GameStatus.VALID, gameStatus);
        assertEquals("1 3 N\r\n5 1 E", endGameReport);
    }
}
