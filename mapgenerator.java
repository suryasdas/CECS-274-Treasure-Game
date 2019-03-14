import java.util.Random;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class mapgenerator {
	public static void main(String[] args) {
		char[][]map=new char[9][9];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				map[i][j]='~';
			}
		}
		int occupiedspots=0;
		int rows=9,cols = 9;
		Random random=new Random();
		while (occupiedspots<5) {
			int x=random.nextInt(rows);
			int y=random.nextInt(cols);
			if (map[x][y]=='~') {
				map[x][y]='X';
				occupiedspots++;
			}
		}
		for (int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		try {
			PrintWriter writer = new PrintWriter("treasures.txt");
			for (int i=0;i<map.length;i++) {
				for(int j=0;j<map.length;j++) {
					writer.print(map[i][j]);
				}
				writer.println();
			}
			writer.close();
		}
		catch(FileNotFoundException fnf) {
			System.out.println("The file was not found");
		}
	}
}
