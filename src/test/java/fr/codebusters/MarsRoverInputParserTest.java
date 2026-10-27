package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.MarsRover;
import fr.codebusters.rover.MarsRoverGameState;
import fr.codebusters.rover.Direction;
import fr.codebusters.rover.MarsRoverInputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarsRoverInputParserTest {

    @Test
    void should_create_correct_game_state_when_giving_correct_file() throws InvalidFormatException {
        List<String> input = List.of("5 5", "1 2 N", "LMLMLMLMM");
        MarsRoverGameState gameState = MarsRoverInputParser.parseInput(input);

        assertEquals(5, gameState.limitX());
        assertEquals(5, gameState.limitY());

        List<MarsRover> rovers = gameState.rovers();
        assertEquals(1, rovers.size());

        MarsRover rover = rovers.getFirst();
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
        assertEquals(Direction.N, rover.getDir());
        assertEquals("LMLMLMLMM", rover.getCommand());
    }

    private static final Collection<Arguments> invalidInputSource = List.of(
            Arguments.arguments(List.of("5 5", "1 2 N")),
            Arguments.arguments(List.of("5 5", "1 N")),
            Arguments.arguments(List.of("5 5", "1 A N")),
            Arguments.arguments(List.of("5 5", "1 2 O"))
    );

    @ParameterizedTest
    @FieldSource("invalidInputSource")
    void should_throw_InvalidFormatException_when_input_is_invalid(List<String> input) {
        assertThrows(InvalidFormatException.class, () -> MarsRoverInputParser.parseInput(input));
    }
}
