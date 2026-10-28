package fr.codebusters.rover.domain;

public class MarsRover implements NasaActionable {

    private Position position;
    private Direction direction;

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }


    public MarsRover(int x, int y, Direction direction) {
        this.position = new Position(x, y);
        this.direction = direction;
    }

    private void turnLeft() {
        this.direction = this.direction.left();
    }

    private void turnRight() {
        this.direction = this.direction.right();
    }

    private void move(Plateau plateau) {
        this.position = plateau.nextPosition(this.position, this.direction);
    }

    public void processAction(Action action, Plateau plateau) {
        switch (action) {
            case LEFT -> turnLeft();
            case RIGHT -> turnRight();
            case MOVE -> move(plateau);
        }
    }

    @Override
    public String toString() {
        return "MarsRover[x=%d, y=%d, dir=%s]".formatted(
                position.x(),
                position.y(),
                direction
        );
    }
}
