package fr.codebusters.rover.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {


    @Test
    void should_turn_left_when_processing_turnLeft() {
        // GIVEN
        MarsRover rover = new MarsRover(0 , 0, Direction.N);

        // WHEN
        rover.processAction(Action.LEFT, new Plateau(1, 1));

        // THEN
        assertEquals(Direction.W, rover.getDirection());
    }

    @Test
    void should_turn_right_when_processing_turnRight() {
        // GIVEN
        MarsRover rover = new MarsRover(0 , 0, Direction.N);

        // WHEN
        rover.processAction(Action.RIGHT, new Plateau(1, 1));

        // THEN
        assertEquals(Direction.E, rover.getDirection());
    }

    private static final Collection<Arguments> shouldMoveOneWhenCallingMoveParams = List.of(
            Arguments.arguments(0, 0, Direction.N, 0, 1),
            Arguments.arguments(0, 1, Direction.S, 0, 0),
            Arguments.arguments(0, 0, Direction.E, 1, 0),
            Arguments.arguments(1, 0, Direction.W, 0, 0),
            Arguments.arguments(0, 0, Direction.W, 1, 0),
            Arguments.arguments(0, 0, Direction.S, 0, 1)
    );

    @ParameterizedTest
    @FieldSource("shouldMoveOneWhenCallingMoveParams")
    void should_move_one_when_processing_move(Integer fromX, Integer fromY, Direction dir, Integer toX, Integer toY) {
        // GIVEN
        MarsRover rover = new MarsRover(fromX , fromY, dir);
        Plateau plateau = new Plateau(1, 1);

        // WHEN
        rover.processAction(Action.MOVE, plateau);

        // THEN
        assertEquals(toX, rover.getPosition().x());
        assertEquals(toY, rover.getPosition().y());
    }

    @Test
    void should_move_around_mars_when_cross_limit() {
        // GIVEN
        MarsRover rover = new MarsRover(0, 0, Direction.N);
        Plateau plateau = new Plateau(1, 1);

        // WHEN
        rover.processAction(Action.MOVE, plateau);
        rover.processAction(Action.MOVE, plateau);

        // THEN
        assertEquals(0, rover.getPosition().x());
        assertEquals(0, rover.getPosition().y());
    }
}
