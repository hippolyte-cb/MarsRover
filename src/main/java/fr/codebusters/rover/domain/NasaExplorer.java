package fr.codebusters.rover.domain;

public interface NasaExplorer {

    Position getPosition();

    Direction getDirection();

    void processAction(Action action, Plateau plateau);
}
