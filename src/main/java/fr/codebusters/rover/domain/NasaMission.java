package fr.codebusters.rover.domain;

import java.util.List;

public record NasaMission(NasaExplorer nasaExplorer, List<Action> actions) {
    public String createReport() {
        return "%d %d %s".formatted(
                nasaExplorer().getPosition().x(),
                nasaExplorer().getPosition().y(),
                nasaExplorer().getDirection()
        );
    }
}
