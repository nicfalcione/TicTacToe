/**
 * Gamestate class
 * 
 * @author Nic Falcione
 * @version 11/24/18
 */
public class GameState {

	// Gamestate constants to make code cleaner
	private static final int CONTINUE_GAME = -1;
	private static final int TIE = 0;
	private static final int PLAYER_WON = 1;
	private static final int CPU_WON = 2;

	/**
	 * Checks the state of the game
	 * 
	 * @return GameState code
	 */
	public static int checkGameState() {
		if (hasPlayerWon()) {
			gameWon();
			return PLAYER_WON;
		}
		if (hasCPUWon()) {
			gameLost();
			return CPU_WON;
		}
		if (isTie()) {
			gameDraw();
			return TIE;
		}
		return CONTINUE_GAME;
	}

	/**
	 * Prints losing message
	 */
	private static void gameLost() {
		System.out.println("The CPU beat you!");
	}

	/**
	 * Prints winning message
	 */
	private static void gameWon() {
		System.out.println("Congratulations you won!");
	}

	/**
	 * Prints tie game message
	 */
	private static void gameDraw() {
		System.out.println("The game is a tie!");
	}

	/**
	 * Checks if there is a token in a chosen coordinate of the board
	 * 
	 * @param coord
	 *            coordinate of the board
	 * @return true or false
	 */
	public static boolean hasToken(int coord) {
		return !TicTacToe.getBoard()[convertRow(coord)][convertCol(coord)].equals("*");
	}

	/**
	 * Checks if there is the player is on a chosen coordinate of the board
	 * 
	 * @param coord
	 *            coordinate of the board
	 * @return true or false
	 */
	public static boolean hasPlayer(int coord) {
		return TicTacToe.getBoard()[convertRow(coord)][convertCol(coord)].equals(TicTacToe.getPlayer().getVal());
	}

	/**
	 * Checks if there is the CPU is on a chosen coordinate of the board
	 * 
	 * @param coord
	 *            coordinate of the board
	 * @return true or false
	 */
	public static boolean hasCPU(int coord) {
		return TicTacToe.getBoard()[convertRow(coord)][convertCol(coord)].equals(TicTacToe.getCPU().getVal());
	}

	/**
	 * Checks to see if the Player has won
	 * 
	 * @return true or false
	 */
	private static boolean hasPlayerWon() {
		if (hasPlayer(1) && hasPlayer(2) && hasPlayer(3)) {
			return true;
		}
		if (hasPlayer(4) && hasPlayer(5) && hasPlayer(6)) {
			return true;
		}
		if (hasPlayer(7) && hasPlayer(8) && hasPlayer(9)) {
			return true;
		}
		if (hasPlayer(1) && hasPlayer(4) && hasPlayer(7)) {
			return true;
		}
		if (hasPlayer(2) && hasPlayer(5) && hasPlayer(8)) {
			return true;
		}
		if (hasPlayer(3) && hasPlayer(6) && hasPlayer(9)) {
			return true;
		}
		if (hasPlayer(1) && hasPlayer(5) && hasPlayer(9)) {
			return true;
		}
		if (hasPlayer(3) && hasPlayer(5) && hasPlayer(7)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if the CPU has won
	 * 
	 * @return true or false
	 */
	private static boolean hasCPUWon() {
		if (hasCPU(1) && hasCPU(2) && hasCPU(3)) {
			return true;
		}
		if (hasCPU(4) && hasCPU(5) && hasCPU(6)) {
			return true;
		}
		if (hasCPU(7) && hasCPU(8) && hasCPU(9)) {
			return true;
		}
		if (hasCPU(1) && hasCPU(4) && hasCPU(7)) {
			return true;
		}
		if (hasCPU(2) && hasCPU(5) && hasCPU(8)) {
			return true;
		}
		if (hasCPU(3) && hasCPU(6) && hasCPU(9)) {
			return true;
		}
		if (hasCPU(1) && hasCPU(5) && hasCPU(9)) {
			return true;
		}
		if (hasCPU(3) && hasCPU(5) && hasCPU(7)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if the game is a tie
	 * 
	 * @return true or false
	 */
	public static boolean isTie() {
		for (int i = 1; i <= 9; i++) {
			if (!hasToken(i)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Converts 2d array column number to coordinate (1-9)
	 * 
	 * @param j
	 *            column number
	 * @return corresponding coordinate on the board
	 */
	public static int convertCol(int j) {
		if (j == 1 || j == 4 || j == 7) {
			return 0;
		} else if (j == 2 || j == 5 || j == 8) {
			return 1;
		}
		return 2;
	}

	/**
	 * Converts 2d array row number to coordinate (1-9)
	 * 
	 * @param j
	 *            row number
	 * @return corresponding coordinate on the board
	 */
	public static int convertRow(int i) {
		if (i < 4) {
			return 0;
		} else if (i < 7) {
			return 1;
		}
		return 2;
	}
}
