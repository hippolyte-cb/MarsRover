package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;

import java.util.List;
import java.util.stream.Collectors;

public record MarsRoverGameState(int limitX, int limitY, List<MarsRover> rovers) {

    public MarsRoverGameState(int limitX, int limitY, List<MarsRover> rovers) {
        this.limitX = limitX;
        this.limitY = limitY;
        this.rovers = List.copyOf(rovers);
    }

    public String processGame() throws InvalidFormatException {
        for (MarsRover rover : rovers) {
            rover.processCommand(limitX, limitY);
        }
        return rovers.stream()
                .map(rover -> "%d %d %s".formatted(rover.getX(), rover.getY(), rover.getDir()))
                .collect(Collectors.joining("\r\n"));
    }
}
