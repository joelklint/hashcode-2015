import java.util.Arrays;

public class Utility {
	
	public static void print(int[][] serverHall) {
		for (int i = 0; i < serverHall.length; i++) {
		    for (int j = 0; j < serverHall[i].length; j++) {
		    	if(serverHall[i][j] == -1) {
		    		System.out.print("XXXX ");
		    	} else if(serverHall[i][j] == 0){
		    		System.out.print("     ");
		    	} else {
		    		System.out.print(String.format("%04d", serverHall[i][j]) + " ");
		    	}
		    }
		    System.out.println();
		}
	}
	
	public static int[][] deepCopy(int[][] original) {
	    if (original == null) {
	        return null;
	    }

	    final int[][] result = new int[original.length][];
	    for (int i = 0; i < original.length; i++) {
	        result[i] = Arrays.copyOf(original[i], original[i].length);
	    }
	    return result;
	}

}
