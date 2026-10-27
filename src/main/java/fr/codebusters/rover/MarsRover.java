package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.dto.Plateau;
import fr.codebusters.rover.dto.Position;

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

    public void move(Plateau plateau) {
        this.position = new Position(
                Math.floorMod(position.x() + dir.stepX, plateau.limitX() + 1),
                Math.floorMod(position.y() + dir.stepY, plateau.limitY() + 1)
        );
    }


    public void processCommand(Character command, Plateau plateau) throws InvalidFormatException {
        switch (command) {
            case 'L' -> turnLeft();
            case 'R' -> turnRight();
            case 'M' -> move(plateau);
            default -> throw new InvalidFormatException("Character %s is not a valid character".formatted(command));
        }
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
