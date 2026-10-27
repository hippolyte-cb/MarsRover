package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.MarsRover;
import fr.codebusters.rover.MarsRoverGameState;
import fr.codebusters.utils.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarsRoverGameStateTest {

    @TempDir
    Path tempDir;

    @Test
    void should_create_correct_game_state_when_giving_correct_file() throws IOException, InvalidFormatException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "5 5\r\n1 2 N\r\nLMLMLMLMM");

        MarsRoverGameState gameState = new MarsRoverGameState(file.toString());

        assertEquals(5, gameState.limitX);
        assertEquals(5, gameState.limitY);

        List<MarsRover> rovers = gameState.getRovers();
        assertEquals(1, rovers.size());

        MarsRover rover = rovers.getFirst();
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
        assertEquals(Direction.N, rover.getDir());
        assertEquals("LMLMLMLMM", rover.getCommand());
    }

    @Test
    void should_throw_IOException_when_file_does_not_exist() {
        assertThrows(IOException.class, () -> new MarsRoverGameState("unknown.txt"));
    }

    @Test
    void should_throw_InvalidFormatException_when_file_has_even_number_of_lines() throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "5 5\r\n1 2 N");

        assertThrows(InvalidFormatException.class, () -> new MarsRoverGameState(file.toString()));
    }

    @Test
    void should_throw_InvalidFormatException_when_file_has_invalid_rover_descriptor() throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "5 5\r\n1 N\r\nLLMM");

        assertThrows(InvalidFormatException.class, () -> new MarsRoverGameState(file.toString()));
    }

    @Test
    void should_throw_InvalidFormatException_when_file_has_rover_with_invalid_coordinate() throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "5 5\r\n1 A N\r\nLLMM");

        assertThrows(InvalidFormatException.class, () -> new MarsRoverGameState(file.toString()));
    }

    @Test
    void should_throw_InvalidFormatException_when_file_has_rover_with_invalid_direction() throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, "5 5\r\n1 0 O\r\nLLMM");

        assertThrows(InvalidFormatException.class, () -> new MarsRoverGameState(file.toString()));
    }
}
