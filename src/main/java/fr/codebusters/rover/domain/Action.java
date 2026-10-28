package fr.codebusters.rover.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Action {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    final Character identity;

    Action(Character identity) {
        this.identity = identity;
    }

    public static Optional<Action> fromChar(char c) {
        return Arrays.stream(values())
                .filter(action -> action.identity == c)
                .findFirst();
    }
}
