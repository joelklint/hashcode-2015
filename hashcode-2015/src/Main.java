import java.io.File;

public class Main {
	public Reader read;
	
	public static void main(String[] args) {
		File file = new File("./dc.in");
		Reader read = new Reader(file);
		read.readFile();
		
		DataLoader dl = new DataLoader(read.unavailable, read.servers);
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 5000; i++) {
			int score = dl.buildMatrixFirstSolution();
			if (score > max) {
				max = score;
				System.out.println("Got new max: " + max);
			}
			if (score < min) {
				min = score;
			}
			if (i % 100 == 0){
				System.out.println("Iterations: " + i);
			}
		}
		System.out.println("max: " + max);
		System.out.println("min: " + min);
	}		
}
