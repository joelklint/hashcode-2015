import java.io.File;

public class SecondMain {
	
	public static void main(String[] args) {
				
				int currentMaxScore = Integer.MIN_VALUE;
				for(int i = 0; i < 2; i++){
					
					File file = new File("./dc.in");
					Reader read = new Reader(file);
					read.readFile();
					RatioAlgorithm ra = new RatioAlgorithm(read);
					ra.performAlgorithm();
					
					Pool[] pools = ra.pools;
					
					int score = ScoreCalculator.getScore(pools);
					if(score > currentMaxScore) {
						currentMaxScore = score;
					}
					System.out.println(score);
				}
				
				System.out.println(currentMaxScore);
				
			}

}
