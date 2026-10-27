package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.MarsRover;
import fr.codebusters.utils.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarsRoverTest {


    @Test
    void should_turn_left_when_calling_turnLeft() {
        MarsRover rover = new MarsRover(0 , 0, Direction.N, "");
        rover.turnLeft();

        assertEquals(Direction.W, rover.getDir());
    }

    @Test
    void should_turn_right_when_calling_turnRight() {
        MarsRover rover = new MarsRover(0 , 0, Direction.N, "");
        rover.turnRight();

        assertEquals(Direction.E, rover.getDir());
    }

    private static final Collection<Arguments> shouldMoveOneWhenCallingMoveParams = List.of(
            Arguments.arguments(0, 0, Direction.N, 0, 1),
            Arguments.arguments(0, 1, Direction.S, 0, 0),
            Arguments.arguments(0, 0, Direction.E, 1, 0),
            Arguments.arguments(1, 0, Direction.W, 0, 0)
    );

    @ParameterizedTest
    @FieldSource("shouldMoveOneWhenCallingMoveParams")
    void should_move_one_when_calling_move(Integer fromX, Integer fromY, Direction dir, Integer toX, Integer toY) {
        MarsRover rover = new MarsRover(fromX , fromY, dir, "");
        rover.move(1, 1);

        assertEquals(toX, rover.getX());
        assertEquals(toY, rover.getY());
    }

    @Test
    void should_move_around_mars_when_cross_limit() {
        MarsRover rover = new MarsRover(0, 0, Direction.N, "");
        rover.move(1, 1);
        rover.move(1, 1);
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
    }

    @Test
    void should_turn_left_when_process_L_command() throws InvalidFormatException {
        MarsRover rover = new MarsRover(0, 0, Direction.N, "L");
        rover.processCommand(1, 1);

        assertEquals(Direction.W, rover.getDir());
    }

    @Test
    void should_turn_right_when_process_R_command() throws InvalidFormatException {
        MarsRover rover = new MarsRover(0, 0, Direction.N, "R");
        rover.processCommand(1, 1);

        assertEquals(Direction.E, rover.getDir());
    }

    @Test
    void should_move_when_process_M_command() throws InvalidFormatException {
        MarsRover rover = new MarsRover(0, 0, Direction.N, "M");
        rover.processCommand(1, 1);

        assertEquals(0, rover.getX());
        assertEquals(1, rover.getY());
    }

    @Test
    void should_throw_invalid_format_exception_when_process_unknown_command() {
        MarsRover rover = new MarsRover(0, 0, Direction.N, "T");
        assertThrows(InvalidFormatException.class, () -> rover.processCommand(1, 1));
    }
}
