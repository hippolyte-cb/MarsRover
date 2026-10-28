package fr.codebusters.rover.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {

    @Test
    void should_give_next_position() {
        // GIVEN
        Plateau p = new Plateau(1, 1);

        // WHEN
        Position nextPosition = p.nextPosition(new Position(0, 0), Direction.N);

        // THEN
        assertEquals(new Position(0, 1), nextPosition);
    }

    @Test
    void should_give_zero_when_reach_the_limit() {
        Plateau p = new Plateau(1, 1);

        // WHEN
        Position nextPositionNorth = p.nextPosition(new Position(1, 1), Direction.N);
        Position nextPositionEast = p.nextPosition(new Position(1, 0), Direction.E);
        Position nextPositionWest = p.nextPosition(new Position(0, 0), Direction.W);
        Position nextPositionSouth = p.nextPosition(new Position(1, 0), Direction.S);

        // THEN
        assertEquals(new Position(1, 0), nextPositionNorth);
        assertEquals(new Position(0, 0), nextPositionEast);
        assertEquals(new Position(1, 0), nextPositionWest);
        assertEquals(new Position(1, 1), nextPositionSouth);
    }

    @Test
    void plateau_should_contain_initial_position() {
        // GIVEN
        Plateau p = new Plateau(1, 1);

        // THEN
        assertTrue(p.contains(new Position(0, 0)));
    }

    @Test
    void plateau_should_contain_limit_position() {
        // GIVEN
        Plateau p = new Plateau(1, 1);

        // THEN
        assertTrue(p.contains(new Position(1, 1)));
    }

    @Test
    void plateau_should_not_contain_outside_position() {
        // GIVEN
        Plateau p = new Plateau(1, 1);

        // THEN
        assertFalse(p.contains(new Position(1, 2)));
        assertFalse(p.contains(new Position(-1, 0)));
    }
}
