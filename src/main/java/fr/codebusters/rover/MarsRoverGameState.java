package fr.codebusters.rover;

import fr.codebusters.utils.Direction;
import fr.codebusters.exception.InvalidFormatException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MarsRoverGameState {

    public final int limitX;
    public final int limitY;

    final List<MarsRover> rovers = new LinkedList<>();

    public List<MarsRover> getRovers() {
        return List.copyOf(rovers);
    }

    public MarsRoverGameState(List<String> input) throws IOException, InvalidFormatException {
        if (input.size() % 2 != 1) {
            throw new InvalidFormatException("There must be an odd number of lines.");
        }

        String[] limits = input.getFirst().split(" ");
        try {
            limitX = Integer.parseInt(limits[0]);
            limitY = Integer.parseInt(limits[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException("First line must contains 2 numbers identify the limit of the map");
        }

        for (int i = 1; i < input.size(); i += 2) {
            String[] roverString = input.get(i).split(" ");
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
    }

    public String processGame() throws InvalidFormatException {
        for (MarsRover rover : rovers) {
            rover.processCommand(limitX, limitY);
        }
        return rovers.stream()
                .map(rover -> "%d %d %s".formatted(rover.getX(), rover.getY(), rover.getDir()))
                .collect(Collectors.joining("\r\n"));
    }
}
