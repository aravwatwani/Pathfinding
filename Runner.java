import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {
	private static boolean cakeFound;
	private static boolean Stack = false;
	private static boolean Queue = false;
	private static boolean inCoordinateForm = false;
	private static boolean outCoordinateForm = false;
	private static ArrayDeque<Point> queue = new ArrayDeque<Point>();
	private static HashMap<Point, Point> newHashMap = new HashMap<Point, Point>();
	private static boolean[][] isVisited;
	private static char[][] map;
	private static Point kirbyStartPoint;
	private static int maxRows;
	private static int maxCols;

	public static void main(String[] args) { // command-line arguments
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--Help")) { // toggles help info. for command-line arguments
				help();
				break;
			}
			if (args[i].equals("--Stack")) { // uses stack algorithm
				Stack = true;
			}
			if (args[i].equals("--Queue")) { // uses queue algorithm
				Queue = true;
			}
			if (args[i].equals("--InCoordinate")) { // converts text-based to coordinate-based
				inCoordinateForm = true;
			}
			if (args[i].equals("--OutCoordinate")) { // converts coordinate-based to text-based
				outCoordinateForm = true;
			}
		}
		String userInput = args[args.length - 1];
		createMap(userInput);
		algorithmSelect();
	}

	public static void algorithmSelect() {

		if (Stack == true) {
			stack();// call stack algorithm
		} else if (Queue == true) {
			queue();// call queue algorithm
		} else if (inCoordinateForm == true || outCoordinateForm == true) {
			if (Stack == true) {
				stack();// call stack algorithm
			} else {
				System.out.println("\nNo algorithm selected. Default to queue algorithm.");
				queue();// call queue algorithm
			}
		} else {
			System.out.println("\nNo algorithm selected. Default to queue algorithm.");
			queue(); // use queue as the default algorithm
		}
	}

	public static void queue() {
		int currentRow = kirbyStartPoint.getRow();
		int currentCol = kirbyStartPoint.getCol();
		queue.add(new Point(currentRow, currentCol));
		isVisited[currentRow][currentCol] = true;
		while (queue.size() > 0) {
			Point p = queue.pop();
			currentRow = p.getRow();
			currentCol = p.getCol();

			// check north tile
			if (currentRow > 0 && !(isVisited[currentRow - 1][currentCol])) {
				Point c = new Point(currentRow - 1, currentCol);
				newHashMap.put(c, p);
				if (map[currentRow - 1][currentCol] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow - 1][currentCol] == '.') {
					queue.add(c);
					isVisited[currentRow - 1][currentCol] = true;
				}
			}
			// check south tile
			if (currentRow < maxRows - 1 && !(isVisited[currentRow + 1][currentCol])) {
				Point c = new Point(currentRow + 1, currentCol);
				newHashMap.put(c, p);
				if (map[currentRow + 1][currentCol] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow + 1][currentCol] == '.') {
					queue.add(c);
					isVisited[currentRow + 1][currentCol] = true;
				}
			}
			// check east tile
			if (currentCol < maxCols - 1 && !(isVisited[currentRow][currentCol + 1])) {
				Point c = new Point(currentRow, currentCol + 1);
				newHashMap.put(c, p);
				if (map[currentRow][currentCol + 1] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow][currentCol + 1] == '.') {
					queue.add(c);
					isVisited[currentRow][currentCol + 1] = true;
				}
			}
			// check west tile
			if (currentCol > 0 && !(isVisited[currentRow][currentCol - 1])) {
				Point c = new Point(currentRow, currentCol - 1);
				newHashMap.put(c, p);
				if (map[currentRow][currentCol - 1] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow][currentCol - 1] == '.') {
					queue.add(c);
					isVisited[currentRow][currentCol - 1] = true;
				}
			}
		}
		if (!cakeFound) {
			System.out.println("The cake cannot be found.");
		}
	}

	public static void stack() {
		int currentRow = kirbyStartPoint.getRow();
		int currentCol = kirbyStartPoint.getCol();
		queue.push(new Point(currentRow, currentCol));
		isVisited[currentRow][currentCol] = true;
		while (queue.size() > 0) {
			Point p = queue.pop();
			currentRow = p.getRow();
			currentCol = p.getCol();
			// check north tile
			if (currentRow > 0 && !(isVisited[currentRow - 1][currentCol])) {
				Point c = new Point(currentRow - 1, currentCol);
				newHashMap.put(c, p);
				if (map[currentRow - 1][currentCol] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow - 1][currentCol] == '.') {
					queue.push(c);
					isVisited[currentRow - 1][currentCol] = true;
				}
			}
			// check south tile
			if (currentRow < maxRows - 1 && !(isVisited[currentRow + 1][currentCol])) {
				Point c = new Point(currentRow + 1, currentCol);
				newHashMap.put(c, p);
				if (map[currentRow + 1][currentCol] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow + 1][currentCol] == '.') {
					queue.push(c);
					isVisited[currentRow + 1][currentCol] = true;
				}
			}
			// check east tile
			if (currentCol < maxCols - 1 && !(isVisited[currentRow][currentCol + 1])) {
				Point c = new Point(currentRow, currentCol + 1);
				newHashMap.put(c, p);
				if (map[currentRow][currentCol + 1] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow][currentCol + 1] == '.') {
					queue.push(c);
					isVisited[currentRow][currentCol + 1] = true;
				}
			}
			// check west tile
			if (currentCol > 0 && !(isVisited[currentRow][currentCol - 1])) {
				Point c = new Point(currentRow, currentCol - 1);
				newHashMap.put(c, p);
				if (map[currentRow][currentCol - 1] == 'C') {
					cakeFound = true;
					generatePath(p);
					break;
				} else if (map[currentRow][currentCol - 1] == '.') {
					queue.push(c);
					isVisited[currentRow][currentCol - 1] = true;
				}
			}
		}
		if (!cakeFound) {
			System.out.println("The cake cannot be found.");
		}
	}

	public static void generatePath(Point c) {
		int row = c.getRow();
		int col = c.getCol();
		while (map[row][col] != 'K') {
			map[row][col] = '+';
			c = newHashMap.get(c);
			row = c.getRow();
			col = c.getCol();
		}
		drawMap();
	}

	public static void createMap(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			maxRows = sc.nextInt();
			maxCols = sc.nextInt();
			map = new char[maxRows][maxCols];
			isVisited = new boolean[maxRows][maxCols];
			int row = 0;
			sc.nextLine();
			if (inCoordinateForm == true) {
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						map[i][j] = '.';
					}
				}
				int r;
				int c;
				String character;
				while (sc.hasNextLine()) {
					character = sc.next();
					r = sc.nextInt();
					c = sc.nextInt();
					map[r][c] = character.charAt(0);
					if (character.charAt(0) == 'K') {
						kirbyStartPoint = new Point(r, c);
					}
					if (character.charAt(0) == 'C') {
						new Point(r, c);
					}
				}
			} else {
				while (sc.hasNextLine()) {
					String line = new String(sc.nextLine());
					for (int col = 0; col < maxCols; col++) {
						map[row][col] = line.charAt(col);
						if (line.charAt(col) == 'K') {
							kirbyStartPoint = new Point(row, col);
						}
						if (line.charAt(col) == 'C') {
							new Point(row, col);
						}
					}
					row++;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void drawMap() {
		if (outCoordinateForm == true) { // takes in a map that is in text-based form, turns it coordinate-based
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j] == '+') {
						System.out.println("+ " + i + " " + j);
					}
				}
			}
		} else { // takes in a map that is in coordinate-based form, turns it text-based
			for (int i = 0; i < map.length; i++) {
				System.out.println();
				for (int j = 0; j < map[0].length; j++) {
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}
	}

	public static void help() {
		System.out.println("Command Line Arguments");
		System.out.println("--Stack (boolean): If this switch is set, use the stack-based approach.");
		System.out.println("--Queue (boolean): If this switch is set, use the queue-based approach.");
		System.out.println("--InCoordinate (boolean): If this switch is set, the input file is in the coordinate-based system. If the switch is not set, the input file is in the text-map format.");
		System.out.println("--OutCoordinate (boolean): If this switch is set, the output file is in the coordinate-based system. If the switch is not set, the output file is in the text-map format.");
	}
}