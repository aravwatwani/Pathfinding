import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class MapReader {
    public static int a = 0;
    public static int b = 0;
    public static int d = 0;
    public static void main(String args[]) {

        File file = new File("map1"); // point to file

        try {

            Scanner sc = new Scanner(file); // setup scanner
            String rows = sc.next();
            String cols = sc.next();
            String levels = sc.next();
            System.out.println(rows + " " + cols + " " + levels);
            int r = Integer.parseInt(rows);
            int c = Integer.parseInt(cols);
            int l = Integer.parseInt(levels);
            String[][][] map = new String[r][c][l];
            while (sc.hasNextLine()) { // check a line exists in the input file
                String temp = "";
                String s = sc.next();
                for (int i = 0; i < s.length(); i++) {
                    int j = i + 1;
                    temp = s.substring(i, j);
                    map[b][a][d] = temp;
                    a++;
                }
                a = 0;
                b++;
                if (b == r) {
                    b = 0;
                    d++;
                }
            }
			sc.close(); // done with scanner
            
            Deque<Point> queue = new ArrayDeque<Point>();
            
			for(int d = 0; d < map[0][0].length; d++){
				for(int a = 0; a < map.length; a++){
					for(int b = 0; b < map[0].length; b++){
						System.out.print(map[a][b][d]);
						if(b == map[0].length - 1){
							System.out.println();
						}
					}
				}
			} 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}