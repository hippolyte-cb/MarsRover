package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.Plateau;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverInputParserTest {

    @Test
    void should_create_correct_game_state_when_giving_correct_file() throws InvalidFormatException {
        List<String> input = List.of("5 5", "1 2 N", "LMLMLMLMM");
        MarsRoverGameState gameState = MarsRoverInputParser.parseInput(input);
        Plateau plateau = gameState.plateau();

        assertEquals(5, plateau.limitX());
        assertEquals(5, plateau.limitY());

        List<RoverMission> missions = gameState.missions();
        assertEquals(1, missions.size());

        MarsRover rover = missions.getFirst().rover();

        assertEquals(1, rover.getPosition().x());
        assertEquals(2, rover.getPosition().y());
        assertEquals(Direction.N, rover.getDir());
        assertEquals("LMLMLMLMM", missions.getFirst().commands());
    }

    private static final Collection<Arguments> invalidInputSource = List.of(
            Arguments.arguments(List.of("5 5", "1 2 N")),
            Arguments.arguments(List.of("5 5", "1 N")),
            Arguments.arguments(List.of("5 5", "1 A N")),
            Arguments.arguments(List.of("5 5", "1 2 O")),
            Arguments.arguments(List.of("-1 5", "0 0 N", "M")),
            Arguments.arguments(List.of("5 5 5", "0 0 N", "M")),
            Arguments.arguments(List.of("5 A", "0 0 N", "M")),
            Arguments.arguments(List.of("5 5", "9 9 N", "M")),
            Arguments.arguments(List.of("5 5", "1 1 N", "MX"))
    );

    @ParameterizedTest
    @FieldSource("invalidInputSource")
    void should_throw_InvalidFormatException_when_input_is_invalid(List<String> input) {
        assertThrows(InvalidFormatException.class, () -> MarsRoverInputParser.parseInput(input));
    }
}
