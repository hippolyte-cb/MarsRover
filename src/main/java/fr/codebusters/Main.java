package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.MarsRoverGameState;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Rover must be call like that:");
            System.err.println("java -jar rover.jar <file>");
            System.exit(1);
            return;
        }

        try {
            MarsRoverGameState gameState = new MarsRoverGameState(args[0]);
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
