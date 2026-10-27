package fr.codebusters.rover;

public enum Direction {
    // Order MUST NOT be changed
    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0);

    public final int stepX;
    public final int stepY;

    Direction(int stepX, int stepY) {
        this.stepX = stepX;
        this.stepY = stepY;
    }

    public Direction left() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction right() {
        return values()[(ordinal() + 1) % 4];
    }
}
