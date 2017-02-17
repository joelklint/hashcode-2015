import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
	
	public Server[] servers;
	public int[][] unavailable;
	
	private Scanner scan;
	private int id;
	private int size;
	private int capacity;
	private int row;
	private int col;
	
	public Reader(File file){
		unavailable = new int[16][100];
		servers = new Server[625];
		id = 0;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		row = 0;
		col = 0;
		scan.nextLine();
		for(int i = 0; i < 80; i++){
			row = scan.nextInt();
			col = scan.nextInt();
			unavailable[row][col] = -1;
		}
		
		while(scan.hasNextInt()){
			size = scan.nextInt();
			capacity = scan.nextInt();
			Server newServer = new Server(id, size, capacity, 0, capacity/size, -1);
			servers[id] = newServer;
			id++;
	
		}
	}
}