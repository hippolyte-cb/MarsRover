package fr.codebusters.rover.domain;

import fr.codebusters.exception.InvalidFormatException;

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
        this.position = plateau.nextPosition(this.position, this.dir);
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
