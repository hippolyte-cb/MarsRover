package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.dto.Plateau;
import fr.codebusters.rover.dto.RoverMission;

import java.util.LinkedList;
import java.util.List;

public class MarsRoverInputParser {
    public static MarsRoverGameState parseInput(List<String> input) throws InvalidFormatException {
        if (input.size() % 2 != 1) {
            throw new InvalidFormatException("There must be an odd number of lines.");
        }

        Plateau plateau = getPlateau(input);
        List<RoverMission> missions = new LinkedList<>();

        for (int i = 1; i < input.size(); i += 2) {
            missions.add(getRoverMission(input, i));
        }

        return new MarsRoverGameState(plateau, missions);
    }

    private static Plateau getPlateau(List<String> input) throws InvalidFormatException {
        Plateau plateau;
        String[] limits = input.getFirst().strip().split("\\s+");
        if (limits.length != 2)
            throw new InvalidFormatException("First line must contains 2 elements");
        try {
            plateau = new Plateau(
                    Integer.parseInt(limits[0]),
                    Integer.parseInt(limits[1])
            );
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException("First line must contains 2 numbers");
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
