package fr.codebusters.rover;

import fr.codebusters.utils.Direction;
import fr.codebusters.exception.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
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

    public MarsRoverGameState(String filename) throws IOException, InvalidFormatException {
        byte[] content;
        try (FileInputStream fis = new FileInputStream(filename)) {
            content = fis.readAllBytes();
        }
        String input = new String(content, Charset.defaultCharset());
        String[] lines = input.split("\r\n");
        if (lines.length % 2 != 1) {
            throw new InvalidFormatException("There must be on odd number of lines.");
        }

        String[] limits = lines[0].split(" ");
        limitX = Integer.parseInt(limits[0]);
        limitY = Integer.parseInt(limits[1]);

        for (int i = 1; i < lines.length; i += 2) {
            String[] roverString = lines[i].split(" ");
            if (roverString.length != 3) {
                throw new InvalidFormatException("Rover descriptor must have 3 fields");
            }
            try {
                rovers.add(
                        new MarsRover(
                                Integer.parseInt(roverString[0]),
                                Integer.parseInt(roverString[1]),
                                Direction.valueOf(roverString[2]),
                                lines[i + 1]
                        )
                );
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("The coordinates of rovers must be valid integers");
            } catch (IllegalArgumentException e) {
                throw new InvalidFormatException("The direction doesn't exists");
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
