package fr.codebusters;

import fr.codebusters.exception.InvalidFormatException;
import fr.codebusters.rover.domain.GameStatus;
import fr.codebusters.rover.MarsRoverInputParser;
import fr.codebusters.rover.MarsRoverGameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // Check input
            Path filePath = checkInput(args);

            // Read file content
            List<String> input = Files.readAllLines(filePath);

            // Parse and create game
            MarsRoverGameState gameState = MarsRoverInputParser.createGame(input);

            // Process game
            GameStatus endGameStatus = gameState.processGame();
            LOGGER.info("End game with status = {}", endGameStatus);

            String endGameReport = gameState.endGameReport();

            // Show endGameReport
            LOGGER.info(endGameReport);

        } catch (IOException e) {
            LOGGER.error("Cannot open the file {}", args[0]);
            System.exit(1);
        } catch (InvalidFormatException e) {
            LOGGER.error(e.getMessage());
            System.exit(1);
        }
    }

    private static Path checkInput(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            LOGGER.error("Rover must be called like that:");
            LOGGER.error("java -jar rover.jar <file>");
            throw new IllegalArgumentException("Expected one argument");
        }

        Path path = Path.of(args[0]);
        if (Files.notExists(path)) {
            throw new FileNotFoundException("File at '%s' doesn't exit".formatted(path));
        }

        return path;
    }
}
