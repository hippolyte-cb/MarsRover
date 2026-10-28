package fr.codebusters.rover.domain;

import java.util.Arrays;

public enum Action {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    final Character identity;

    Action(Character identity) {
        this.identity = identity;
    }

    public static Action fromChar(char c) {
        return Arrays.stream(values())
                .filter(action -> action.identity == c)
                .findFirst().orElse(null);
    }
}
