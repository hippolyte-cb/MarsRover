package fr.codebusters.rover;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.*;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverInputParser {
    public static MarsRoverGameState createGame(List<String> input) throws InvalidFormatException {
        if (input.size() % 2 != 1) {
            throw new InvalidFormatException("There must be an odd number of lines.");
        }

        Plateau plateau = createPlateau(input);
        List<NasaMission> missions = new ArrayList<>(input.size() / 2);

        for (int i = 1; i < input.size() - 1; i += 2) {
            String roverDescriptor = input.get(i);
            String roverCommand = input.get(i + 1);
            NasaMission mission = createRoverMission(roverDescriptor, roverCommand);

            if (!plateau.contains(mission.nasaActionable().getPosition())) {
                throw new InvalidFormatException("Rover must be in plateau");
            }
            missions.add(mission);
        }

        return new MarsRoverGameState(plateau, missions);
    }

    private static Plateau createPlateau(List<String> input) throws InvalidFormatException {
        String[] limits = input.getFirst().strip().split("\\s+");
        if (limits.length != 2)
            throw new InvalidFormatException("First line must contain 2 elements");
        try {
            var plateau = new Plateau(
                    Integer.parseInt(limits[0]),
                    Integer.parseInt(limits[1])
            );

            if (plateau.isInvalid())
                throw new InvalidFormatException("One of plateau's limits is negative");

            return plateau;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidFormatException("First line must contain 2 numbers");
        }
    }

    private static NasaMission createRoverMission(String roverDescriptor, String roverCommand) throws InvalidFormatException {
        String[] roverString = roverDescriptor.strip().split("\\s+");
        if (roverString.length != 3) {
            throw new InvalidFormatException("Rover descriptor must have 3 fields");
        }
        try {
            List<Action> actions = createActions(roverCommand);
            return new NasaMission(
                    new MarsRover(
                            Integer.parseInt(roverString[0]),
                            Integer.parseInt(roverString[1]),
                            Direction.valueOf(roverString[2])
                    ),
                    actions
            );
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("The coordinates of rovers must be valid integers");
        } catch (IllegalArgumentException e) {
            throw new InvalidFormatException("The direction doesn't exist");
        }
    }

    private static List<Action> createActions(String roverCommand) throws InvalidFormatException {
        List<Action> actions = new ArrayList<>(roverCommand.length());
        for (char c : roverCommand.toCharArray()) {
            Action action = Action.fromChar(c);
            if (action == null) throw new InvalidFormatException("Rover command is not valid");
            actions.add(action);
        }
        return actions;
    }
}
