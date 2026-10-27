package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.Direction;
import fr.codebusters.rover.MarsRover;
import fr.codebusters.rover.MarsRoverGameState;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverGameStateTest {

    @Test
    void should_return_final_positions_when_process_game() throws InvalidFormatException {
        LinkedHashMap<MarsRover, List<String>> roverCommandMap = new LinkedHashMap<>();
        roverCommandMap.put(new MarsRover(1, 2, Direction.N),  List.of("LMLMLMLMM"));
        roverCommandMap.put(new MarsRover(3, 3, Direction.E), List.of("MMRMMRMRRM"));

        var game = new MarsRoverGameState(5, 5, roverCommandMap);
        assertEquals("1 3 N\r\n5 1 E", game.processGame());
    }
}
