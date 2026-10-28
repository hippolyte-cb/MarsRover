package fr.codebusters.rover;

import fr.codebusters.rover.domain.Action;
import fr.codebusters.rover.domain.GameStatus;
import fr.codebusters.rover.domain.Plateau;
import fr.codebusters.rover.domain.NasaMission;

import java.util.List;
import java.util.stream.Collectors;

public final class MarsRoverGameState {
    private final Plateau plateau;
    private final List<NasaMission> missions;


    public MarsRoverGameState(Plateau plateau, List<NasaMission> missions) {
        this.plateau = plateau;
        this.missions = missions;
    }

    public GameStatus processGame() {
        for (NasaMission mission : missions) {
            for (Action a : mission.commands()) {
                mission.nasaActionable().processAction(a, plateau);
            }
        }

        return GameStatus.VALID;
    }

    public String endGameReport() {
        return missions
                .stream()
                .map(NasaMission::createReport)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public Plateau plateau() {
        return plateau;
    }

    public List<NasaMission> missions() {
        return missions;
    }

    @Override
    public String toString() {
        return "MarsRoverGameState[" +
                "plateau=" + plateau + ", " +
                "missions=" + missions + ']';
    }

}
