package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverInputParserTest {

    @Test
    void should_create_correct_game_state_when_giving_correct_file() throws InvalidFormatException {
        List<String> input = List.of("5 5", "1 2 N", "LMLMLMLMM");
        MarsRoverGameState gameState = MarsRoverInputParser.parseInput(input);
        Plateau plateau = gameState.plateau();

        assertEquals(5, plateau.limitX());
        assertEquals(5, plateau.limitY());

        Map<MarsRover, List<String>> rovers = gameState.roversCommandMap();
        assertEquals(1, rovers.size());

        Optional<MarsRover> optionalRover = rovers.keySet().stream().findFirst();
        assertTrue(optionalRover.isPresent());
        MarsRover rover = optionalRover.get();

        assertEquals(1, rover.getPosition().x());
        assertEquals(2, rover.getPosition().y());
        assertEquals(Direction.N, rover.getDir());
        assertEquals(1, rovers.get(rover).size());
        assertEquals("LMLMLMLMM", rovers.get(rover).getFirst());
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
