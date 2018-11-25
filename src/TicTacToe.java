import java.util.Scanner;

/**
 * Main class for TicTacToe game
 * 
 * @author Nic Falcione
 * @version 11/24/18
 *
 */
public class TicTacToe {
	// Used to clean code
	private static final int CONTINUE_GAME = -1;
	private static final int CPU_STRATEGY_NOT_NEEDED = -1;

	// Delays for animating strings
	private static final int PRINT_DELAY = 1300;
	private static final int COUNT_DELAY = 333;

	// CPU AI Difficulty constants
	private static final String NOVICE = "1";
	private static final String INTERMEDIATE = "2";
	private static final String EXPERT = "3";

	// Coin constants
	private static final String HEADS = "Heads";
	private static final String TAILS = "Tails";

	// Used when cpu wins coin toss
	private static boolean cpuStarts;

	private static String difficulty;

	private static token player;
	private static token cpu;

	private static String[][] board = new String[3][3];

	/**
	 * Enumeration used to represent taken coordinates on the board as tokens. ("x"
	 * and "o" in TICTACTOE game)
	 */
	enum token {

		X("X"), O("O");

		private final String tok;

		private token(String t) {
			tok = t;
		}

		public String getVal() {
			return tok;
		}
	};

	/**
	 * Main method
	 * 
	 * @param args
	 *            command line parameters
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		player = getToken(in);

		if (player == token.X) {
			cpu = token.O;
		} else {
			cpu = token.X;
		}

		getDifficulty(in);

		if (!difficulty.equals(NOVICE)) {
			String coin = getCoin(in);

			if (coin.equals(flipCoin())) {
				wonCoinToss();
				cpuStarts = false;
			} else {
				lostCoinToss();
				cpuStarts = true;
			}
		}

		initializeBoard();
		printDirections();
		printBoard();

		try {
			playGame(in);
		} catch (InterruptedException e) {
			System.out.println("Something went wrong!");
		} catch (Exception e) {

		}

		in.close();
	}

	/**
	 * This method is where the magic happens. The game and its components are
	 * tracked here.
	 * 
	 * @param s
	 *            Scanner from main to read user command line inputs
	 * @throws InterruptedException
	 *             thrown if the Thread.sleep(delay) calls fail
	 */
	public static void playGame(Scanner s) throws InterruptedException {

		// If cpu wins the coin toss, a move is made
		if (cpuStarts) {
			// Animates command line for readability
			Thread.sleep(PRINT_DELAY);

			System.out.println("Now the CPU will play.");

			int CPUMove = CPUAI.randomStrategy();
			placeToken(CPUMove, cpu);

			Thread.sleep(PRINT_DELAY);

			printBoard();
			cpuStarts = false;
		}

		// This loop is where the user moves are read and made and the CPU AI moves are
		// made.
		while (true) {
			System.out.println("Choose where to play.");
			int choice = s.nextInt();
			if (choice > 0 && choice < 10) {
				if (!GameState.hasCPU(choice) && !GameState.hasPlayer(choice)) {
					placeToken(choice, player);
				} else {
					System.out.println("Choose another board location.");
					continue;
				}
			} else {
				System.out.println("Choice not recognized.");
			}

			printBoard();

			if (GameState.checkGameState() != CONTINUE_GAME) {
				break;
			}

			Thread.sleep(PRINT_DELAY);

			System.out.println("Now the CPU will play.");

			int CPUMove;
			if (CPUAI.winStrategy() != CPU_STRATEGY_NOT_NEEDED) {
				CPUMove = CPUAI.winStrategy();
				placeToken(CPUMove, cpu);
			} else if (CPUAI.defensiveStrategy() != CPU_STRATEGY_NOT_NEEDED && !difficulty.equals(NOVICE)) {
				CPUMove = CPUAI.defensiveStrategy();
				placeToken(CPUMove, cpu);
			} else if (CPUAI.aggresiveStrategy() != CPU_STRATEGY_NOT_NEEDED && difficulty.equals(EXPERT)) {
				CPUMove = CPUAI.aggresiveStrategy();
				placeToken(CPUMove, cpu);
			} else {
				CPUMove = CPUAI.randomStrategy();
				placeToken(CPUMove, cpu);
			}

			Thread.sleep(PRINT_DELAY);

			printBoard();

			if (GameState.checkGameState() != CONTINUE_GAME) {
				break;
			}
		}
	}

	/**
	 * Getter method for the board
	 * 
	 * @return 2d string array representation of the board
	 */
	public static String[][] getBoard() {
		return board;
	}

	/**
	 * Getter for the player's token
	 * 
	 * @return player's token
	 */
	public static token getPlayer() {
		return player;
	}

	/**
	 * Getter for the CPU's token
	 * 
	 * @return CPU's token
	 */
	public static token getCPU() {
		return cpu;
	}

	/**
	 * Initialize's empty board
	 */
	public static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = "*";
			}
		}
	}

	/**
	 * Used to randomly flip a coin (50/50 chance) which determines who starts
	 * first.
	 * 
	 * @return Heads or Tails
	 */
	public static String flipCoin() {
		// Used to animate a countdown in the command line
		try {
			System.out.print("\n3");
			Thread.sleep(COUNT_DELAY);
			System.out.print(".");
			Thread.sleep(COUNT_DELAY);
			System.out.print(". ");
			Thread.sleep(COUNT_DELAY);
			System.out.print("2");
			Thread.sleep(COUNT_DELAY);
			System.out.print(".");
			Thread.sleep(COUNT_DELAY);
			System.out.print(". ");
			Thread.sleep(COUNT_DELAY);
			System.out.print("1");
			Thread.sleep(COUNT_DELAY);
			System.out.print(".");
			Thread.sleep(COUNT_DELAY);
			System.out.print(".\n");
		} catch (InterruptedException e) {
			System.out.println("Something went wrong!");
		}

		// Generates a number between 0.0 and 1.0, which simulates flipping a coin
		if (Math.random() < 0.5) {
			System.out.println("Heads\n");
			return HEADS;
		}
		System.out.println("Tails\n");
		return TAILS;
	}

	/**
	 * Prints coin toss win message
	 */
	public static void wonCoinToss() {
		System.out.println("Congratulations, you've won the coin toss!\nYou get to go first!");
	}

	/**
	 * Prints coin toss lost message
	 */
	public static void lostCoinToss() {
		System.out.println("Ouch! You've lost the coin toss.\nCPU goes first!");
	}

	/**
	 * Places cpu or player token on the board
	 * 
	 * @param choice
	 *            coordinate to be placed
	 * @param t
	 *            token to be placed
	 */
	public static void placeToken(int choice, token t) {
		board[GameState.convertRow(choice)][GameState.convertCol(choice)] = t.getVal();
	}

	/**
	 * Prints board to command line
	 */
	public static void printBoard() {
		System.out.println("");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (j != 2) {
					System.out.print(board[i][j] + " ");
				} else {
					System.out.print(board[i][j] + " " + "\n");
				}
			}
		}
		System.out.println();
	}

	/**
	 * Prints the directions to the game
	 */
	public static void printDirections() {
		System.out.println(
				"\nTo play the game, use #s 1-9, where 1 is the \ntop left and 9 is the bottom right. Here is\nthe empty board to start.");
	}

	/**
	 * Gets the user's token choice for the command line
	 * 
	 * @param s
	 *            Scanner from main to read from command line
	 * @return player token
	 */
	public static token getToken(Scanner s) {
		while (true) {
			System.out.println("Would you like to be X or O?");
			String choice = s.next().toUpperCase();

			if (choice.equals("X")) {
				return token.X;
			} else if (choice.equals("O")) {
				return token.O;
			} else {
				System.out.println("\nChoice not recognized.");
			}
		}
	}

	/**
	 * Gets the user's choice of game difficulty
	 * 
	 * @param s
	 *            Scanner from main to read from command line
	 */
	public static void getDifficulty(Scanner s) {
		while (true) {
			System.out.println(
					"\nWhat difficulty would you like the CPU to be?\n1 : NOVICE\n2 : INTERMEDIATE\n3 : EXPERT");
			String choice = s.next();

			if (choice.equals(NOVICE)) {
				difficulty = NOVICE;
				return;
			} else if (choice.equals(INTERMEDIATE)) {
				difficulty = INTERMEDIATE;
				return;
			} else if (choice.equals(EXPERT)) {
				difficulty = EXPERT;
				return;
			} else {
				System.out.println("\nChoice not recognized.");
			}
		}
	}

	/**
	 * Gets the user's choice of coin side
	 * 
	 * @param s
	 *            Scanner from main to read from command line
	 */
	public static String getCoin(Scanner s) {
		while (true) {
			System.out.println(
					"\nLet's Flip a coin to see who goes first.\nWould you like to be Heads or Tails?\n1 : Heads\n2 : Tails");
			String choice = s.next();

			if (choice.equals("1")) {
				return HEADS;
			} else if (choice.equals("2")) {
				return TAILS;
			} else {
				System.out.println("\nChoice not recognized.");
			}
		}
	}
}
