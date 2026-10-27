package fr.codebusters.rover.domain;

import fr.codebusters.rover.MarsRover;

public record RoverMission(MarsRover rover, String commands) {
}
