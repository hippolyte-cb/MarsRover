package fr.codebusters.rover.dto;

public record Plateau(int limitX, int limitY) {
    public Plateau {
        if (limitX < 0 || limitY < 0) throw new IllegalArgumentException("Plateau limits must be >= 0");
    }
}
