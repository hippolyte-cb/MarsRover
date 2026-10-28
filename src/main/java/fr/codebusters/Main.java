package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.GameStatus;
import fr.codebusters.rover.MarsRoverInputParser;
import fr.codebusters.rover.MarsRoverGameState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO logger;
        /*
        TODO: Explain why there are comments
         */
        try {
            // Check input
            Path filePath = checkInput(args);

            // Read file content
            List<String> input = Files.readAllLines(filePath);

            // Parse and create game
            MarsRoverGameState gameState = MarsRoverInputParser.createGame(input);

            // Process game
            GameStatus endGameStatus = gameState.processGame();
            System.out.printf("End game with status = %s\n", endGameStatus);

            String endGameReport = gameState.endGameReport();

            // Show endGameReport
            System.out.println(endGameReport);

        } catch (IOException e) {
            System.err.printf("Cannot open the file %s\n", args[0]);
            System.exit(1);
        } catch (InvalidFormatException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Path checkInput(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println("Rover must be called like that:");
            System.err.println("java -jar rover.jar <file>");
            throw new IllegalArgumentException("Expected one argument");
        }

        Path path = Path.of(args[0]);
        if (Files.notExists(path)) {
            throw new FileNotFoundException("File at '%s' doesn't exit".formatted(path));
        }

        return path;
    }
}
