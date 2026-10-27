package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;

import java.util.LinkedList;
import java.util.List;

public class MarsRoverInputParser {
    public static MarsRoverGameState parseInput(List<String> input) throws InvalidFormatException {
        if (input.size() % 2 != 1) {
            throw new InvalidFormatException("There must be an odd number of lines.");
        }

        int limitX, limitY;
        List<MarsRover> rovers = new LinkedList<>();
        String[] limits = input.getFirst().strip().split("\\s+");
        try {
            limitX = Integer.parseInt(limits[0]);
            limitY = Integer.parseInt(limits[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException("First line must contains 2 numbers identify the limit of the map");
        }

        for (int i = 1; i < input.size(); i += 2) {
            String[] roverString = input.get(i).strip().split("\\s+");
            if (roverString.length != 3) {
                throw new InvalidFormatException("Rover descriptor must have 3 fields");
            }
            try {
                rovers.add(
                        new MarsRover(
                                Integer.parseInt(roverString[0]),
                                Integer.parseInt(roverString[1]),
                                Direction.valueOf(roverString[2]),
                                input.get(i + 1)
                        )
                );
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("The coordinates of rovers must be valid integers");
            } catch (IllegalArgumentException e) {
                throw new InvalidFormatException("The direction doesn't exist");
            }
        }

        return new MarsRoverGameState(limitX, limitY, rovers);
    }
}
