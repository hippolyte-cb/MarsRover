package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.Direction;
import fr.codebusters.rover.domain.Plateau;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverInputParser {
    public static MarsRoverGameState parseInput(List<String> input) throws InvalidFormatException {
        if (input.size() % 2 != 1) {
            throw new InvalidFormatException("There must be an odd number of lines.");
        }

        Plateau plateau = getPlateau(input);
        List<RoverMission> missions = new ArrayList<>(input.size() / 2);

        for (int i = 1; i < input.size(); i += 2) {
            RoverMission mission = getRoverMission(input, i);
            if (!plateau.contains(mission.rover().getPosition())) {
                throw new InvalidFormatException("Rover must be in plateau");
            }
            if (!mission.commands().matches("[LRM]*")) {
                throw new InvalidFormatException("Rover command is not valid");
            }
            missions.add(mission);
        }

        return new MarsRoverGameState(plateau, missions);
    }

    private static Plateau getPlateau(List<String> input) throws InvalidFormatException {
        Plateau plateau;
        String[] limits = input.getFirst().strip().split("\\s+");
        if (limits.length != 2)
            throw new InvalidFormatException("First line must contain 2 elements");
        try {
            plateau = new Plateau(
                    Integer.parseInt(limits[0]),
                    Integer.parseInt(limits[1])
            );
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException("First line must contain 2 numbers");
        } catch (IllegalArgumentException e) {
            throw new InvalidFormatException("One of plateau's limits is negative");
        }
        return plateau;
    }

    private static RoverMission getRoverMission(List<String> input, int i) throws InvalidFormatException {
        String[] roverString = input.get(i).strip().split("\\s+");
        if (roverString.length != 3) {
            throw new InvalidFormatException("Rover descriptor must have 3 fields");
        }
        try {
            return new RoverMission(
                    new MarsRover(
                            Integer.parseInt(roverString[0]),
                            Integer.parseInt(roverString[1]),
                            Direction.valueOf(roverString[2])
                    ),
                    input.get(i + 1)
            );
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("The coordinates of rovers must be valid integers");
        } catch (IllegalArgumentException e) {
            throw new InvalidFormatException("The direction doesn't exist");
        }
    }
}
