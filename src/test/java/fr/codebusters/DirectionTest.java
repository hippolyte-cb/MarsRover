package fr.codebusters;

import fr.codebusters.utils.Direction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    private static final Collection<Arguments> leftFromToDirection = List.of(
            Arguments.of(Direction.N, Direction.W),
            Arguments.of(Direction.W, Direction.S),
            Arguments.of(Direction.S, Direction.E),
            Arguments.of(Direction.E, Direction.N)
    );

    @ParameterizedTest
    @FieldSource("leftFromToDirection")
    void should_turn_left_when_calling_left(Direction from, Direction expected) {
        assertEquals(expected, from.left());
    }

    private static final List<Arguments> rightFromToDirection = List.of(
            Arguments.of(Direction.N, Direction.E),
            Arguments.of(Direction.E, Direction.S),
            Arguments.of(Direction.S, Direction.W),
            Arguments.of(Direction.W, Direction.N)
    );

    @ParameterizedTest
    @FieldSource("rightFromToDirection")
    void should_turn_left_when_calling_right(Direction from, Direction expected) {
        assertEquals(expected, from.right());
    }
}
