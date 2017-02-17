import java.io.File;

public class OptimalMain {
	
	static final int RANDOM = 0, RATIO = 1, RANDOM_SERVERS = 2, RANDOM_POOLS = 3;
	
	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++) {
			new OptimalMain().start(RANDOM_POOLS);
			System.out.println("Try again...");
		}
	}
	
	private void start(int type) {
		// Read from input file
		File file = new File("./dc.in");
		Reader read = new Reader(file);
		read.readFile();
		
		// Set-up algorithm structure
		Algorithm algorithm = null;
		
		switch (type) {
		case OptimalMain.RANDOM:
			algorithm = new RandomAlgorithm(read.unavailable, read.servers);
			break;
		case OptimalMain.RATIO:
			algorithm = new RatioAlgorithm(read);
			break;
		case OptimalMain.RANDOM_SERVERS:
			algorithm = new RandomServersAlgorithm(read.unavailable, read.servers);
			break;
		case OptimalMain.RANDOM_POOLS:
			algorithm = new RandomPoolsAlgorithm(read.unavailable, read.servers);
			break;
		default:
			break;
		}
		
		// Perform algorithm
		int times = 1000000;
		int max = 0;
		int min = Integer.MAX_VALUE;
		int[][] bestServerHall = null;
		int[][] worstServerHall = null;
		System.out.print("New max: ");
		
		for (int i = 0; i < times; i++) {
			algorithm.performAlgorithm();
			int score = ScoreCalculator.getScore(algorithm.getPools());
			if (score > max) {
				max = score;
				bestServerHall = Utility.deepCopy(algorithm.getServerHall());
				System.out.print(max + ", ");
			}
			if (score < min) {
				min = score;
				worstServerHall = Utility.deepCopy(algorithm.getServerHall());
			}
		}
		
		// Prints the best output serverhall with servers with min and max values
		System.out.println();
		System.out.println("Max: " + max);
		System.out.println("Min: " + min);
		System.out.println();
		
		if(max > 400) {
			Utility.print(bestServerHall);
		}
	}
}
