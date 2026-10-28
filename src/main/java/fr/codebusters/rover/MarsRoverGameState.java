package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.Plateau;
import fr.codebusters.rover.domain.RoverMission;

import java.util.List;
import java.util.stream.Collectors;

public final class MarsRoverGameState {
    private final Plateau plateau;
    private final List<RoverMission> missions;


    public MarsRoverGameState(Plateau plateau, List<RoverMission> missions) {
        this.plateau = plateau;
        this.missions = List.copyOf(missions);
    }

    public String processGame() throws InvalidFormatException {
        for (RoverMission mission : missions) {
            for (char c : mission.commands().toCharArray()) {
                mission.rover().processCommand(c, plateau);
            }
        }
        return missions
                .stream()
                .map(mission -> "%d %d %s".formatted(
                        mission.rover().getPosition().x(),
                        mission.rover().getPosition().y(),
                        mission.rover().getDir()
                ))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public Plateau plateau() {
        return plateau;
    }

    public List<RoverMission> missions() {
        return missions;
    }

    @Override
    public String toString() {
        return "MarsRoverGameState[" +
                "plateau=" + plateau + ", " +
                "missions=" + missions + ']';
    }

}
