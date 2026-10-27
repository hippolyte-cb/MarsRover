package fr.codebusters.rover.dto;

import fr.codebusters.rover.MarsRover;

public record RoverMission(MarsRover rover, String commands) {
}
