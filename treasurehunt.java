/**CECS 274 Project 1
 * Author: Surya S Das
 * Date: 09-24-2018
 * Instructor: shannon cleary
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
/**
 * 
 * @author suryadas
 * @description main part of the code where the game is executed.
 * 
 */
public class treasurehunt {
	public static void main(String[] args) {
		char[][] map1=loadmap(); 
		char[][] map2= loadmap(); 

		boolean again = true;
		Random random = new Random();

		while (again) {
			boolean cond = false;
			int treasures= 5;
			int option = 0;

			clearboard(map2);

			int x=random.nextInt(9);
			int y=random.nextInt(9);



			while (option != 2 && treasures != 0) {
				System.out.println("\n" + treasures + " treasures left.");
				displayboard(map2, x, y, cond);
				if (cond) {
					System.out.println("Look out! Pirate spotted!!");
					cond = false;
				}

				System.out.println("Treasure Hunt:\n1. Hunt\n2. Quit");
				option = menuoption();

				if (option == 1) {
					System.out.print("Enter row letter (A-I): ");
					int rowinput=rowoption();

					System.out.print("Enter column number (1-9): ");
					int colinput=coloption();

					while (true) {
						map2[x][y] = '~';
						int location = random.nextInt(4);
						if (location == 0) {
							if (x == 0) {
								x++;
							} else {
								x--;
							}
						} else if (location == 1) {
							if (y == 8) {
								y--;
							} else {
								y++;
							}
						} else if (location == 2) {
							if (x == 8) {
								x--;
							} else {
								x++;
							}
						} else {
							if (y == 0) {
								y++;
							} else {
								y--;
							}
						}
						if (map1[x][y] != 'X' && map2[x][y] != 'C' && map2[x][y] != 'H') {
							map2[x][y] = 'P';
							break;
						}
					}

					if (map2[rowinput][colinput - 1] == 'P') {
						cond = true;
						System.out.println("\n" + treasures + " treasures left.");
						displayboard(map2, x, y, cond);
						System.out.println("\"Arrrr! Ye been caught searchin\' for me treasure.\"\nYou have been captured by the Pirate!!");
						System.out.print("You Lose! ");
						break;
					}

					char clue = getclue(map1, rowinput, colinput - 1);
					if (clue == 'H') {
						cond = true;
					}
					if (map2[rowinput][colinput - 1] != '~') {
						System.out.println("The spot is already taken.");
					} else {
						map2[rowinput][colinput - 1] = clue;
						if (clue == 'X') {
							treasures--;
						}
					}

				}				
			}
			if (option == 2) {
				break;
			}
			System.out.print("Try again (Y/N)? ");
			again = replay();
			if (again == false) {
				System.out.print("Game Over.");
			}
		}		
	}

	/**
	 * @description allows the user to choose between hunt or to quit the game.
	 * @return menu which is the user option.
	 */
	public static int menuoption() {
		int menu=CheckInput.getIntRange(1, 2);
		return menu;
	}
	/**
	 * @description allows the user to choose the column number.
	 * @return col which is the user option.
	 */
	public static int coloption() {
		int col=CheckInput.getIntRange(1, 9);
		return col;
	}

	/**
	 * @description allows the user to choose the row letter.
	 * @return row which is the user option.
	 */
	public static char rowoption() {
		int row =chartoint(CheckInput.getChar());
		return (char) row;
	}

	/**
	 * @description allows the user to replay the game after they have won/lost.
	 * @return replay which is the user option.
	 */
	public static boolean replay() {
		boolean replay=CheckInput.getYesNo();
		return replay;
	}

	/**
	 * @description loads the map from the mapgenerator program and reads the text file.
	 * @return returns the map with the treasure.
	 */
	public static char[][] loadmap() {
		char[][] map1 = new char[9][9];
		try {
			Scanner s = new Scanner(new File("treasures.txt"));
			String a;
			for (int r=0;r<map1.length;r++) {
				a=s.nextLine();
				for(int c=0;c<map1.length;c++) {
					map1[r][c]=a.charAt(c);
				}
			}
		}
		catch(FileNotFoundException fnf){
			System.out.print("File not found.");
		}
		return map1;
	}

	/**
	 * @description clears the board and returns only an array of "~"'s.
	 * @param map
	 */
	public static void clearboard(char[][] map){
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				map[i][j]='~';
			}
		}
	}

	/**
	 * @description checks for the character input and convert into an int value for calculations.
	 * @param a
	 * @return the value of the character
	 */
	public static int chartoint(char a) {
		switch (a) {
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		default:
			return 8;
		}
	}
	/**
	 * @displays a board with row letters and column numbers with the array.
	 * @param map
	 * @param row
	 * @param col
	 * @param cond
	 */
	public static void displayboard(char[][] map, int row, int col, boolean cond) {
		char[] alpha = {'A','B','C','D','E','F','G','H','I'};
		System.out.println("  1 2 3 4 5 6 7 8");
		for (int r=0;r<map.length;r++) {
			System.out.print(alpha[r] + " ");
			for(int c=0;c<map.length;c++) {
				if (r == row && c == col) {
					if (cond == true) {
						System.out.print('P');
					}
					else {
						System.out.print('~');
					}
				}
				else {
					System.out.print(map[r][c]);
				}
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	/**
	 * @description checks the location for the user input of rows and columns. displays H if treasure is near and c
	 * @param map1
	 * @param row
	 * @param col
	 * @return
	 */
	public static char getclue(char[][] map1, int row, int col) {
		char[][] map2 = new char[11][11];
		for (int r = 0; r < map2.length; r++) {
			for (int c = 0; c < map2.length; c++) {
				map2[r][c]='~';
			}
		}
		for (int r = 0; r < map1.length; r++) {
			for (int c = 0; c < map1.length; c++) {
				if (map1[r][c] == 'X') {
					map2[r][c] = 'X';
				}
			}
		}
		char clue = 'C';
		if (map1[row][col] == 'X') {
			return clue = 'X';
		}
		int temprow = row;
		int tempcol = col;
		if (row == 0) {
			temprow = 1;
		}
		if (col == 0) {
			tempcol = 1;
		}
		for (int r = temprow - 1; r <= row + 2; r++) {
			for (int c = tempcol - 1; c <= col + 1; c++) {
				if (map2[r][c] == 'X') {
					clue = 'H';
				}
			}
		}
		return clue;
	}
}