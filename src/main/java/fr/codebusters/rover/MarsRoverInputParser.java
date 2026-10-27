package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.SequencedMap;

public class MarsRoverInputParser {
    public static MarsRoverGameState parseInput(List<String> input) throws InvalidFormatException {
        if (input.size() % 2 != 1) {
            throw new InvalidFormatException("There must be an odd number of lines.");
        }

        int limitX, limitY;
        SequencedMap<MarsRover, List<String>> rovers = new LinkedHashMap<>();
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
                rovers.put(
                        new MarsRover(
                                Integer.parseInt(roverString[0]),
                                Integer.parseInt(roverString[1]),
                                Direction.valueOf(roverString[2])
                        ),
                        List.of(input.get(i + 1))
                );
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("The coordinates of roversCommandMap must be valid integers");
            } catch (IllegalArgumentException e) {
                throw new InvalidFormatException("The direction doesn't exist");
            }
        }

        return new MarsRoverGameState(limitX, limitY, rovers);
    }
}
