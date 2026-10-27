package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;

import java.util.*;
import java.util.stream.Collectors;

public record MarsRoverGameState(Plateau plateau, SequencedMap<MarsRover, List<String>> roversCommandMap) {

    public MarsRoverGameState(Plateau plateau, SequencedMap<MarsRover, List<String>> roversCommandMap) {
        this.plateau = plateau;
        this.roversCommandMap = Collections.unmodifiableSequencedMap(roversCommandMap);
    }

    public String processGame() throws InvalidFormatException {
        for (MarsRover rover : roversCommandMap.keySet()) {
            for (String command : roversCommandMap().get(rover)) {
                for (int i = 0; i < command.length(); i++) {
                    rover.processCommand(command.charAt(i), plateau);
                }
            }
        }
        return roversCommandMap
                .keySet()
                .stream()
                .map(rover -> "%d %d %s".formatted(rover.getPosition().x(), rover.getPosition().y(), rover.getDir()))
                .collect(Collectors.joining("\r\n"));
    }
}
