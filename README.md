# Mars Rover

Java implementation of the Mars Rover kata: a squad of rovers is deployed on a rectangular plateau and moved with simple commands. The program reads an input file and prints each rover's final position and heading.

## Requirements

- Java 21
- Maven 3.9+

## Build and run

```bash
mvn package
java -jar target/rover.jar input.txt
```

Run the tests only:

```bash
mvn test
```

## Input format

```
5 5          <- upper-right corner of the plateau (lower-left is 0 0)
1 2 N        <- rover 1: x, y and heading (N, E, S, W)
LMLMLMLMM    <- rover 1: commands
3 3 E        <- rover 2: position
MMRMMRMRRM   <- rover 2: commands
```

Commands:

| Command | Action | Effect |
|---|---|---|
| `L` | `LEFT` | Spin 90° left, without moving |
| `R` | `RIGHT` | Spin 90° right, without moving |
| `M` | `MOVE` | Move forward one grid point, keeping the same heading |

The square directly North of `(x, y)` is `(x, y+1)`. Rovers move one after the other: the second rover starts only once the first one has finished.

## Output

One line per rover, in input order: final coordinates and heading.

```
1 3 N
5 1 E
```

## Design choices

- **Plateau edges (wrap-around):** a rover that moves past an edge reappears on the opposite side, e.g. `2 5 N` + `M` on a `5 5` plateau ends at `2 0 N`. The rule lives in `Plateau.nextPosition`, so it can be changed in one place (for instance to keep the rover on the edge instead).
- **Collisions:** not handled. Two rovers can end up on the same grid point.
- **Validation before execution:** the whole file is parsed and checked before any rover moves. Command letters are turned into typed `Action` values at parsing time, so the domain never receives an invalid command and an error on one rover never leaves the others half-processed.
- **Layers:** `Main` handles the command line and the file, `MarsRoverInputParser` turns the input lines into domain objects, `MarsRoverGameState` runs the missions, and the `domain` package holds the model (`Plateau`, `Position`, `Direction`, `Action`, `MarsRover`). The domain depends on no other package of the project.
- **Extensibility:** a mission drives any `NasaExplorer`, not only a `MarsRover`, so another kind of vehicle can be added without changing the game loop. `processGame` returns a `GameStatus` (currently always `VALID`) so that outcomes such as collisions can be reported later.

## Errors

The program prints a message on stderr when:

- no file, or more than one, is passed on the command line;
- the file does not exist or cannot be read;
- the input is invalid:
  - the file does not have an odd number of lines (plateau line + 2 lines per rover);
  - the first line is not exactly two integers, or a limit is negative;
  - a rover position does not have exactly 3 fields, its coordinates are not integers, or its heading is not `N`, `E`, `S` or `W`;
  - a rover starts outside the plateau;
  - a command line contains anything other than `L`, `R` and `M`.
