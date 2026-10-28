package fr.codebusters.rover.domain;

public record Plateau(int limitX, int limitY) {
    public Plateau {
        if (limitX < 0 || limitY < 0) throw new IllegalArgumentException("Plateau limits must be >= 0");
    }

    public boolean contains(Position pos) {
        return 0 <= pos.x() && pos.x() <= limitX && 0 <= pos.y() && pos.y() <= limitY;
    }

    public Position nextPosition(Position position, Direction dir) {
        return new Position(
                Math.floorMod(position.x() + dir.stepX, limitX + 1),
                Math.floorMod(position.y() + dir.stepY, limitY + 1)
        );
    }
}
