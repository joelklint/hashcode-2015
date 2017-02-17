import java.io.File;

public class Main {
	
	static final int RANDOM = 0, RATIO = 1, RANDOM_SERVERS = 2;
	
	public static void main(String[] args) {
		new Main().start(RATIO);
	}
	
	private void start(int type) {
		// Read from input file
		File file = new File("./dc.in");
		Reader read = new Reader(file);
		read.readFile();
		
		// Prints the input serverhall
		Utility.print(read.unavailable);
		
		// Set-up algorithm structure
		Algorithm algorithm = null;
		
		switch (type) {
		case Main.RANDOM:
			algorithm = new RandomAlgorithm(read.unavailable, read.servers);
			break;
		case Main.RATIO:
			algorithm = new RatioAlgorithm(read);
			break;
		case Main.RANDOM_SERVERS:
			algorithm = new RandomServersAlgorithm(read.unavailable, read.servers);
			break;
		default:
			break;
		}
		
		// Perform algorithm
		int times = 5000;
		int max = 0;
		int min = Integer.MAX_VALUE;
		int[][] bestServerHall = null;
		int[][] worstServerHall = null;
		
		for (int i = 0; i < times; i++) {
			algorithm.performAlgorithm();
			int score = ScoreCalculator.getScore(algorithm.getPools());
			if (score > max) {
				max = score;
				bestServerHall = Utility.deepCopy(algorithm.getServerHall());
				System.out.print("Got new max: " + max + ", ");
			}
			if (score < min) {
				min = score;
				worstServerHall = Utility.deepCopy(algorithm.getServerHall());
			}
			if (i % 1000 == 0){
				System.out.print("Iteration: " + i / 1000 + "K, ");
			}
		}
		
		// Prints the best output serverhall with servers with min and max values
		System.out.println();
		System.out.println("Max: " + max);
		System.out.println("Min: " + min);
		
		Utility.print(bestServerHall);
		System.out.println();
		Utility.print(worstServerHall);
	}
}
