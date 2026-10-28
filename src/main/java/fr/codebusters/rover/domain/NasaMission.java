package fr.codebusters.rover.domain;

import java.util.List;

public record NasaMission(NasaActionable nasaActionable, List<Action> commands) {
    public String createReport() {
        return "%d %d %s".formatted(
                nasaActionable().getPosition().x(),
                nasaActionable().getPosition().y(),
                nasaActionable().getDirection()
        );
    }
}
