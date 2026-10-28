package fr.codebusters.rover.domain;

public interface NasaActionable {

    Position getPosition();

    Direction getDirection();

    void processAction(Action action, Plateau plateau);
}
