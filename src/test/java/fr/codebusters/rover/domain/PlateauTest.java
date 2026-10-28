package fr.codebusters.rover.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {

    @Test
    void should_give_next_position() {
        Plateau p = new Plateau(1, 1);
        assertEquals(new Position(0, 1), p.nextPosition(new Position(0, 0), Direction.N));
    }

    @Test
    void should_give_zero_when_reach_the_limit() {
        Plateau p = new Plateau(1, 1);
        assertEquals(new Position(1, 0), p.nextPosition(new Position(1, 1), Direction.N));
        assertEquals(new Position(0, 0), p.nextPosition(new Position(1, 0), Direction.E));
        assertEquals(new Position(1, 0), p.nextPosition(new Position(0, 0), Direction.W));
        assertEquals(new Position(1, 1), p.nextPosition(new Position(1, 0), Direction.S));
    }

    @Test
    void plateau_should_contain_initial_position() {
        Plateau p = new Plateau(1, 1);
        assertTrue(p.contains(new Position(0, 0)));
    }

    @Test
    void plateau_should_contain_limit_position() {
        Plateau p = new Plateau(1, 1);
        assertTrue(p.contains(new Position(1, 1)));
    }

    @Test
    void plateau_should_not_contain_outside_position() {
        Plateau p = new Plateau(1, 1);
        assertFalse(p.contains(new Position(1, 2)));
        assertFalse(p.contains(new Position(-1, 0)));
    }
}
