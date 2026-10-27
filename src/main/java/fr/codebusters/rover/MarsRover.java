package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;

import java.util.Map;
import java.util.Optional;

public class MarsRover {

    private Position position;
    private Direction dir;

    public Position getPosition() {
        return position;
    }

    public Direction getDir() {
        return dir;
    }


    public MarsRover(int x, int y, Direction dir) {
        this.position = new Position(x, y);
        this.dir = dir;
    }

    public void turnLeft() {
        this.dir = this.dir.left();
    }

    public void turnRight() {
        this.dir = this.dir.right();
    }

    public void move(int limitX, int limitY) {
        this.position = new Position(
                Math.floorMod(position.x() + dir.stepX, limitX + 1),
                Math.floorMod(position.y() + dir.stepY, limitY + 1)
        );
    }


    public void processCommand(Character command, Plateau plateau) throws InvalidFormatException {
        final Map<Character, Runnable> lookupTable = Map.of(
                'L', this::turnLeft,
                'R', this::turnRight,
                'M', () -> this.move(plateau.limitX(), plateau.limitY())
        );

        Optional.ofNullable(lookupTable.get(command))
                .orElseThrow(() -> new InvalidFormatException("Character %s is not a valid character".formatted(command)))
                .run();
    }

    @Override
    public String toString() {
        return "MarsRover[x=%d, y=%d, dir=%s]".formatted(
                position.x(),
                position.y(),
                dir
        );
    }
}
