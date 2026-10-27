package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;

import java.util.Map;
import java.util.Optional;

public class MarsRover {

    private int x;
    private int y;
    private Direction dir;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDir() {
        return dir;
    }


    public MarsRover(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void turnLeft() {
        this.dir = this.dir.left();
    }

    public void turnRight() {
        this.dir = this.dir.right();
    }

    private static final Map<Direction, int[]> dirStepMapping = Map.of(
            Direction.N, new int[] {0, 1},
            Direction.S, new int[] {0, -1},
            Direction.E, new int[] {1, 0},
            Direction.W, new int[] {-1, 0}
    );

    public void move(int limitX, int limitY) {
        int[] step = dirStepMapping.get(this.dir);
        int stepX = step[0], stepY = step[1];

        x = Math.floorMod(x + stepX, limitX + 1);
        y = Math.floorMod(y + stepY, limitY + 1);
    }


    public void processCommand(Character command, int limitX, int limitY) throws InvalidFormatException {
        final Map<Character, Runnable> lookupTable = Map.of(
                'L', this::turnLeft,
                'R', this::turnRight,
                'M', () -> this.move(limitX, limitY)
        );

        Optional.ofNullable(lookupTable.get(command))
                .orElseThrow(() -> new InvalidFormatException("Character %s is not a valid character".formatted(command)))
                .run();
    }

    @Override
    public String toString() {
        return "MarsRover[x=%d, y=%d, dir=%s]".formatted(
                x,
                y,
                dir
        );
    }
}
