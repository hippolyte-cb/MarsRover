package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.Direction;
import fr.codebusters.rover.domain.MarsRover;
import fr.codebusters.rover.domain.Plateau;
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
        MarsRover rover = new MarsRover(0 , 0, Direction.N);
        rover.turnLeft();

        assertEquals(Direction.W, rover.getDir());
    }

    @Test
    void should_turn_right_when_calling_turnRight() {
        MarsRover rover = new MarsRover(0 , 0, Direction.N);
        rover.turnRight();

        assertEquals(Direction.E, rover.getDir());
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
    void should_move_one_when_calling_move(Integer fromX, Integer fromY, Direction dir, Integer toX, Integer toY) {
        MarsRover rover = new MarsRover(fromX , fromY, dir);
        Plateau plateau = new Plateau(1, 1);
        rover.move(plateau);

        assertEquals(toX, rover.getPosition().x());
        assertEquals(toY, rover.getPosition().y());
    }

    @Test
    void should_move_around_mars_when_cross_limit() {
        MarsRover rover = new MarsRover(0, 0, Direction.N);
        Plateau plateau = new Plateau(1, 1);
        rover.move(plateau);
        rover.move(plateau);
        assertEquals(0, rover.getPosition().x());
        assertEquals(0, rover.getPosition().y());
    }

    @Test
    void should_turn_left_when_process_L_command() throws InvalidFormatException {
        MarsRover rover = new MarsRover(0, 0, Direction.N);
        rover.processCommand('L', new Plateau(1, 1));

        assertEquals(Direction.W, rover.getDir());
    }

    @Test
    void should_turn_right_when_process_R_command() throws InvalidFormatException {
        MarsRover rover = new MarsRover(0, 0, Direction.N);
        rover.processCommand('R', new Plateau(1, 1));

        assertEquals(Direction.E, rover.getDir());
    }

    @Test
    void should_move_when_process_M_command() throws InvalidFormatException {
        MarsRover rover = new MarsRover(0, 0, Direction.N);
        rover.processCommand('M', new Plateau(1, 1));

        assertEquals(0, rover.getPosition().x());
        assertEquals(1, rover.getPosition().y());
    }

    @Test
    void should_throw_invalid_format_exception_when_process_unknown_command() {
        MarsRover rover = new MarsRover(0, 0, Direction.N);
        assertThrows(InvalidFormatException.class, () -> rover.processCommand('T', new Plateau(1, 1)));
    }
}
