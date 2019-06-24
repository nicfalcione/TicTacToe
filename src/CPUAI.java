import java.util.Random;

/**
 * Class to create CPU AI
 * 
 * @author Nic Falcione
 * @version 6/24/19
 */
public class CPUAI {

    // Direction constants stored as integers to make the code cleaner
    private static final int INVALID = -1;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    // Constant used when a AI strategy is not needed
    private static final int CPU_STRATEGY_NOT_NEEDED = -1;

    // Map of neighboring coordinates of each coordinate on the board. "-1" is
    // used
    // if the coordinate direction is out of bounds.
    private static final int[][] searchMap = { { -1, 4, -1, 2 },
            { -1, 5, 1, 3 }, { -1, 6, 2, -1 }, { 1, 7, -1, 5 }, { 2, 8, 4, 6 },
            { 3, 9, 5, -1 }, { 4, -1, -1, 8 }, { 5, -1, 7, 9 },
            { 6, -1, 8, -1 } };

    /**
     * This method calculates coordinates next to cpu tokens to force CPU try to
     * make match
     * 
     * @return coordinate of move if needed
     */
    public static int aggresiveStrategy() {
        for (int i = 1; i <= 9; i++) {
            if (GameState.hasCPU(i)) {
                if (searchMap[i - 1][UP] != INVALID
                        && !GameState.hasToken(searchMap[i - 1][UP])) {
                    return searchMap[i - 1][UP];
                }
                if (searchMap[i - 1][DOWN] != INVALID
                        && !GameState.hasToken(searchMap[i - 1][DOWN])) {
                    return searchMap[i - 1][DOWN];
                }
                if (searchMap[i - 1][LEFT] != INVALID
                        && !GameState.hasToken(searchMap[i - 1][LEFT])) {
                    return searchMap[i - 1][LEFT];
                }
                if (searchMap[i - 1][RIGHT] != INVALID
                        && !GameState.hasToken(searchMap[i - 1][RIGHT])) {
                    return searchMap[i - 1][RIGHT];
                }
            }
        }
        return CPU_STRATEGY_NOT_NEEDED;
    }

    /**
     * This method checks if user can make a win and blocks it
     * 
     * @return coordinate of move if needed
     */
    public static int defensiveStrategy() {
        // Check First row
        if (GameState.hasPlayer(1) && GameState.hasPlayer(2)
                && !GameState.hasToken(3)) {
            return 3;
        }
        if (GameState.hasPlayer(2) && GameState.hasPlayer(3)
                && !GameState.hasToken(1)) {
            return 1;
        }
        if (GameState.hasPlayer(1) && GameState.hasPlayer(3)
                && !GameState.hasToken(2)) {
            return 2;
        }

        // Check Second Row
        if (GameState.hasPlayer(4) && GameState.hasPlayer(5)
                && !GameState.hasToken(6)) {
            return 6;
        }
        if (GameState.hasPlayer(5) && GameState.hasPlayer(6)
                && !GameState.hasToken(4)) {
            return 4;
        }
        if (GameState.hasPlayer(4) && GameState.hasPlayer(6)
                && !GameState.hasToken(5)) {
            return 5;
        }

        // Check Third row
        if (GameState.hasPlayer(7) && GameState.hasPlayer(8)
                && !GameState.hasToken(9)) {
            return 9;
        }
        if (GameState.hasPlayer(8) && GameState.hasPlayer(9)
                && !GameState.hasToken(7)) {
            return 7;
        }
        if (GameState.hasPlayer(7) && GameState.hasPlayer(9)
                && !GameState.hasToken(8)) {
            return 8;
        }

        // Check First Column
        if (GameState.hasPlayer(1) && GameState.hasPlayer(4)
                && !GameState.hasToken(7)) {
            return 7;
        }
        if (GameState.hasPlayer(4) && GameState.hasPlayer(7)
                && !GameState.hasToken(1)) {
            return 1;
        }
        if (GameState.hasPlayer(1) && GameState.hasPlayer(7)
                && !GameState.hasToken(4)) {
            return 4;
        }

        // Check Second column
        if (GameState.hasPlayer(2) && GameState.hasPlayer(5)
                && !GameState.hasToken(8)) {
            return 8;
        }
        if (GameState.hasPlayer(5) && GameState.hasPlayer(8)
                && !GameState.hasToken(2)) {
            return 2;
        }
        if (GameState.hasPlayer(2) && GameState.hasPlayer(8)
                && !GameState.hasToken(5)) {
            return 5;
        }

        // Check Third Column
        if (GameState.hasPlayer(3) && GameState.hasPlayer(6)
                && !GameState.hasToken(9)) {
            return 9;
        }
        if (GameState.hasPlayer(6) && GameState.hasPlayer(9)
                && !GameState.hasToken(3)) {
            return 3;
        }
        if (GameState.hasPlayer(3) && GameState.hasPlayer(9)
                && !GameState.hasToken(6)) {
            return 6;
        }

        // Check Diagonals
        if (GameState.hasPlayer(1) && GameState.hasPlayer(5)
                && !GameState.hasToken(9)) {
            return 9;
        }
        if (GameState.hasPlayer(5) && GameState.hasPlayer(9)
                && !GameState.hasToken(1)) {
            return 1;
        }
        if (GameState.hasPlayer(3) && GameState.hasPlayer(5)
                && !GameState.hasToken(7)) {
            return 7;
        }
        if (GameState.hasPlayer(7) && GameState.hasPlayer(5)
                && !GameState.hasToken(3)) {
            return 3;
        }
        if (GameState.hasPlayer(1) && GameState.hasPlayer(9)
                && !GameState.hasToken(5)) {
            return 5;
        }
        if (GameState.hasPlayer(3) && GameState.hasPlayer(7)
                && !GameState.hasToken(5)) {
            return 5;
        }

        return CPU_STRATEGY_NOT_NEEDED;
    }

    /**
     * This method forces cpu to choose a winning coordinate if possible
     * 
     * @return coordinate of move if needed
     */
    public static int winStrategy() {
        // Check First row
        if (GameState.hasCPU(1) && GameState.hasCPU(2)
                && !GameState.hasToken(3)) {
            return 3;
        }
        if (GameState.hasCPU(2) && GameState.hasCPU(3)
                && !GameState.hasToken(1)) {
            return 1;
        }
        if (GameState.hasCPU(1) && GameState.hasCPU(3)
                && !GameState.hasToken(2)) {
            return 2;
        }

        // Check Second Row
        if (GameState.hasCPU(4) && GameState.hasCPU(5)
                && !GameState.hasToken(6)) {
            return 6;
        }
        if (GameState.hasCPU(5) && GameState.hasCPU(6)
                && !GameState.hasToken(4)) {
            return 4;
        }
        if (GameState.hasCPU(4) && GameState.hasCPU(6)
                && !GameState.hasToken(5)) {
            return 5;
        }

        // Check Third row
        if (GameState.hasCPU(7) && GameState.hasCPU(8)
                && !GameState.hasToken(9)) {
            return 9;
        }
        if (GameState.hasCPU(8) && GameState.hasCPU(9)
                && !GameState.hasToken(7)) {
            return 7;
        }
        if (GameState.hasCPU(7) && GameState.hasCPU(9)
                && !GameState.hasToken(8)) {
            return 8;
        }

        // Check First Column
        if (GameState.hasCPU(1) && GameState.hasCPU(4)
                && !GameState.hasToken(7)) {
            return 7;
        }
        if (GameState.hasCPU(4) && GameState.hasCPU(7)
                && !GameState.hasToken(1)) {
            return 1;
        }
        if (GameState.hasCPU(1) && GameState.hasCPU(7)
                && !GameState.hasToken(4)) {
            return 4;
        }

        // Check Second column
        if (GameState.hasCPU(2) && GameState.hasCPU(5)
                && !GameState.hasToken(8)) {
            return 8;
        }
        if (GameState.hasCPU(5) && GameState.hasCPU(8)
                && !GameState.hasToken(2)) {
            return 2;
        }
        if (GameState.hasCPU(2) && GameState.hasCPU(8)
                && !GameState.hasToken(5)) {
            return 5;
        }

        // Check Third Column
        if (GameState.hasCPU(3) && GameState.hasCPU(6)
                && !GameState.hasToken(9)) {
            return 9;
        }
        if (GameState.hasCPU(6) && GameState.hasCPU(9)
                && !GameState.hasToken(3)) {
            return 3;
        }
        if (GameState.hasCPU(3) && GameState.hasCPU(9)
                && !GameState.hasToken(6)) {
            return 6;
        }

        // Check Diagonals
        if (GameState.hasCPU(1) && GameState.hasCPU(5)
                && !GameState.hasToken(9)) {
            return 9;
        }
        if (GameState.hasCPU(5) && GameState.hasCPU(9)
                && !GameState.hasToken(1)) {
            return 1;
        }
        if (GameState.hasCPU(3) && GameState.hasCPU(5)
                && !GameState.hasToken(7)) {
            return 7;
        }
        if (GameState.hasCPU(7) && GameState.hasCPU(5)
                && !GameState.hasToken(3)) {
            return 3;
        }
        if (GameState.hasCPU(1) && GameState.hasCPU(9)
                && !GameState.hasToken(5)) {
            return 5;
        }
        if (GameState.hasCPU(3) && GameState.hasCPU(7)
                && !GameState.hasToken(5)) {
            return 5;
        }

        return CPU_STRATEGY_NOT_NEEDED;
    }

    /**
     * This method makes a random, but valid move
     * 
     * @return coordinate of move
     */
    public static int randomStrategy() {
        boolean cpuMoveChosen = false;
        Random rand = new Random();
        int coord = -1;

        // Chooses random coordinate between 1-9 that is empty
        while (!cpuMoveChosen) {
            coord = rand.nextInt((9 - 1) + 1) + 1;
            if (!GameState.hasToken(coord)) {
                cpuMoveChosen = true;
            }
        }
        return coord;
    }
}