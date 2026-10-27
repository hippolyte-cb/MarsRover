package fr.codebusters.rover;

import java.util.Map;

public enum Direction {
    N,
    E,
    S,
    W;

    private static final Map<Direction, Direction> leftMapping = Map.of(
            Direction.N, Direction.W,
            Direction.W, Direction.S,
            Direction.S, Direction.E,
            Direction.E, Direction.N
    );
    private static final Map<Direction, Direction> rightMapping = Map.of(
            Direction.N, Direction.E,
            Direction.E, Direction.S,
            Direction.S, Direction.W,
            Direction.W, Direction.N
    );

    public Direction left() {
        return leftMapping.get(this);
    }

    public Direction right() {
        return rightMapping.get(this);
    }
}
