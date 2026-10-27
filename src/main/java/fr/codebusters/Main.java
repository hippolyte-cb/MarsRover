package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.MarsRoverGameState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Rover must be called like that:");
            System.err.println("java -jar rover.jar <file>");
            System.exit(1);
            return;
        }

        try {
            List<String> input = Files.readAllLines(Path.of(args[0]));
            MarsRoverGameState gameState = new MarsRoverGameState(input);
            System.out.println(gameState.processGame());
        } catch (IOException e) {
            System.err.printf("Cannot open the file %s\n", args[0]);
            System.exit(1);
        } catch (InvalidFormatException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
