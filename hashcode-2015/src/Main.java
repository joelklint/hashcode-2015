import java.io.File;

public class Main {
	public Reader read;
	
	public static void main(String[] args) {
		File file = new File("./dc.in");
		Reader read = new Reader(file);
		read.readFile();
		RatioAlgorithm ra = new RatioAlgorithm(read);
		ra.performAlgorithm();
	}
		
	
	
}
