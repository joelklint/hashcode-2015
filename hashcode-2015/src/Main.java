import java.io.File;

public class Main {
	
	static final int RANDOM = 0, RATIO = 1;
	
	public static void main(String[] args) {
		new Main().start(RANDOM);
	}
	
	private void start(int type) {
		// Read from input file
		File file = new File("./dc.in");
		Reader read = new Reader(file);
		read.readFile();
		
		// Set-up algorithm structure
		Algorithm algorithm = null;
		
		switch (type) {
		case Main.RANDOM:
			algorithm = new RandomAlgorithm(read.unavailable, read.servers);
			break;
		case Main.RATIO:
			algorithm = new RatioAlgorithm(read);
		default:
			break;
		}
		
		// Perform algorithm
		int times = 5000;
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < times; i++) {
			algorithm.performAlgorithm();
			int score = ScoreCalculator.getScore(algorithm.getPools());
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
		
		System.out.println("Max: " + max);
		System.out.println("Min: " + min);
	}
}
